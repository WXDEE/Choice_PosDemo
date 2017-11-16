package com.choice.service.impl;

import com.choice.common.Const;
import com.choice.common.ServerResponse;
import com.choice.entity.Dish;
import com.choice.entity.DishCatelog;
import com.choice.mapper.DishCatelogMapper;
import com.choice.mapper.DishMapper;
import com.choice.mapper.JedisClient;
import com.choice.service.DishService;
import com.choice.util.DateTimeUtil;
import com.choice.util.JsonUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DishServiceImpl implements DishService {
	private Logger logger = Logger.getLogger(DishServiceImpl.class);
	@Autowired
	private DishMapper dishMapper;
	@Autowired
	private DishCatelogMapper dishCatelogMapper;

	@Autowired
	private JedisClient jedisClient;
	public ServerResponse<List<Dish>> queryDishByCatelog(String catelog) throws Exception{

		String json = jedisClient.hget(Const.DISH_CACHE, catelog);
		if(!StringUtils.isBlank(json)) {
			jedisClient.expire(Const.DISH_CACHE, 1000);
			List<Dish> dishList = JsonUtils.jsonToList(json, Dish.class);
			return ServerResponse.createBySuccess(dishList);
		}
		List<Dish> dishList = dishMapper.selectDishByCatelog(catelog);
		List<DishCatelog> dishCatelogList = dishCatelogMapper.selectList();
		Map<String,String> map = new HashMap();
		for(DishCatelog dishCatelog : dishCatelogList){
			map.put(dishCatelog.getId().toString(),dishCatelog.getDcName());
		}
		for(Dish dish : dishList){
			dish.setDcId(map.get(dish.getDcId()));
		}
		json = JsonUtils.objectToJson(dishList);
		jedisClient.hset(Const.DISH_CACHE, catelog, json);
		jedisClient.expire(Const.DISH_CACHE, 1000);
		return ServerResponse.createBySuccess(dishList);
	}

	public ServerResponse addDish(Dish dish) throws Exception{
		String date = DateTimeUtil.dateToStr(new Date(),"yyyy-MM-dd HH:mm:ss");
		dish.setdDate(date);
		Integer result = dishMapper.insertDish(dish);
		if(result.equals(1)){
			logger.info("新增菜品"+dish.toString());
			return ServerResponse.createBySuccess();
		}else {
			return ServerResponse.createByError();
		}
	}

	public ServerResponse updateDish(Dish dish) throws Exception{
		Integer result = dishMapper.updateDish(dish);
		if(result.equals(1)){
			logger.info("修改菜品"+dish.toString());
			return ServerResponse.createBySuccess();
		}else {
			return ServerResponse.createByError();
		}
	}

	public ServerResponse deleteDish(Integer id) throws Exception{
		Integer result = dishMapper.deleteDishById(id);
		if(result.equals(1)){
			logger.info("删除菜品"+id);
			return ServerResponse.createBySuccess();
		}else {
			return ServerResponse.createByError();
		}
	}

	public ServerResponse<List<Dish>> queryDishByCn(String cn) throws Exception{
		List<Dish> dishList = dishMapper.selectDishByCn(cn);
		List<DishCatelog> dishCatelogList = dishCatelogMapper.selectList();
		Map<String,String> map = new HashMap();
		for(DishCatelog dishCatelog : dishCatelogList){
			map.put(dishCatelog.getId().toString(),dishCatelog.getDcName());
		}
		for(Dish dish : dishList){
			dish.setDcId(map.get(dish.getDcId()));
		}
		return ServerResponse.createBySuccess(dishList);
	}

	public ServerResponse<List<Dish>> queryDishByNameAndDate(String dName, String sdDate, String edDate) throws Exception{

		if(sdDate == null || edDate == null ||
				StringUtils.isBlank(sdDate) ||
				StringUtils.isBlank(edDate) ||
				sdDate.equals("undefined") ||
				edDate.equals("undefined")){
			sdDate = null;
			edDate = null;
		}
		if(sdDate != null){
			sdDate = sdDate+" 00:00:00";
			edDate = edDate+" 23:59:59";
		}
		List<Dish> dishList = dishMapper.selectDishByDNameAndDDate(dName, sdDate,edDate);
		List<DishCatelog> dishCatelogList = dishCatelogMapper.selectList();
		Map<String,String> map = new HashMap();
		for(DishCatelog dishCatelog : dishCatelogList){
			map.put(dishCatelog.getId().toString(),dishCatelog.getDcName());
		}
		for(Dish dish : dishList){
			dish.setDcId(map.get(dish.getDcId()));
		}
		return ServerResponse.createBySuccess(dishList);

	}

	public ServerResponse<PageInfo> queryDish(Integer pageNum, Integer pageSize) throws Exception{
		PageHelper.startPage(pageNum,pageSize);
		List<Dish> dishList = dishMapper.selectDish();
		PageInfo pageInfo = new PageInfo(dishList);
		return ServerResponse.createBySuccess(pageInfo);
	}
	/***
	 * 查询售空菜品列表
	 */
	public ServerResponse<List<Dish>> queryDishWithNone()throws Exception {
		List<Dish> emptyDishList = dishMapper.selectEmptyDish();
		ServerResponse<List<Dish>> result = ServerResponse.createBySuccess(emptyDishList);
		return result;
	}
	/***
	 * 查询售空菜品数量
	 */
	public ServerResponse<String> queryDishCountWithNone() throws Exception{
		Integer count = dishMapper.selectEmptyCount();
		ServerResponse<String> result = ServerResponse.createBySuccess(""+count);
		return result;
	}
	/***
	 * 查询余量不足菜品数量
	 */
	public ServerResponse<String> queryDishCountithNotEnough() throws Exception{
		Integer count = dishMapper.selectNotEnoughCount(Const.DHSI_NUM);
		ServerResponse<String> result = ServerResponse.createBySuccess(""+count);
		return result;
	}
	/***
	 * 查询菜品所有数量
	 */
	public ServerResponse<String> queryDishCount() throws Exception{
		Integer count = dishMapper.selectDishCount();
		ServerResponse<String> result = ServerResponse.createBySuccess(""+count);
		return result;
	}
}
