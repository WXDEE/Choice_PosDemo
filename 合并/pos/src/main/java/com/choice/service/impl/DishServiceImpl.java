package com.choice.service.impl;

import com.choice.common.Const;
import com.choice.common.ServerResponse;
import com.choice.dto.OrdersDTO;
import com.choice.entity.Dish;
import com.choice.entity.DishCatelog;
import com.choice.mapper.DishCatelogMapper;
import com.choice.mapper.DishMapper;
import com.choice.mapper.JedisClient;
import com.choice.service.DishService;
import com.choice.service.MQService;
import com.choice.util.DateTimeUtil;
import com.choice.util.JsonUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.base.Strings;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

@Service
public class DishServiceImpl implements DishService {
	private Logger logger = Logger.getLogger(DishServiceImpl.class);
	@Autowired
	private DishMapper dishMapper;
	@Autowired
	private DishCatelogMapper dishCatelogMapper;
	@Autowired
	private ThreadPoolTaskExecutor taskExecutor;
	@Autowired
	@Qualifier("DishMQService")
	private MQService mQService;
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

		DishUndefinedToNull(dish);
		String date = DateTimeUtil.dateToStr(new Date(),"yyyy-MM-dd HH:mm:ss");
		dish.setdDate(date);
		Integer result = dishMapper.insertDish(dish);
		if(result.equals(1)){
			jedisClient.expire(Const.DISH_CACHE, 0);
			logger.info("新增菜品"+dish.toString());
			printDish(dish);
			return ServerResponse.createBySuccess();
		}else {
			return ServerResponse.createByError();
		}
	}


	/**
	 * 异步向mq发送消息
	 * @param dish
	 */
	public void printDish(Dish dish){
		//从线程池中取出一个线程向mq发送订单消息
		taskExecutor.execute(new Runnable() {
			@Override
			public void run() {
				mQService.sendMessage(JsonUtils.objectToJson(dish));
			}
		});
	}

	public ServerResponse updateDish(Dish dish) throws Exception{

		DishUndefinedToNull(dish);
		Integer result = dishMapper.updateDish(dish);
		if(result.equals(1)){
			jedisClient.expire(Const.DISH_CACHE, 0);
			logger.info("修改菜品"+dish.toString());
			return ServerResponse.createBySuccess();
		}else {
			return ServerResponse.createByError();
		}
	}

	public ServerResponse deleteDish(Integer id) throws Exception{
		Integer result = dishMapper.deleteDishById(id);
		if(result.equals(1)){
			jedisClient.expire(Const.DISH_CACHE, 0);
			logger.info("删除菜品"+id);
			return ServerResponse.createBySuccess();
		}else {
			return ServerResponse.createByError();
		}
	}

	public ServerResponse<List<Dish>> queryDishByCn(String cn) throws Exception{
		List<Dish> dishList = dishMapper.selectDishByCn(cn);
		dishCatelogIdToName(dishList);
		return ServerResponse.createBySuccess(dishList);
	}

	public ServerResponse<List<Dish>> queryDishByNameAndDate(String dName, String sdDate, String edDate) throws Exception{

		if(Strings.isNullOrEmpty(sdDate) || Strings.isNullOrEmpty(edDate)||
				sdDate.equals("undefined") || edDate.equals("undefined")) {
			sdDate = null;
			edDate = null;
		}
		List<Dish> dishList = getDishList(dName, sdDate, edDate);
		return ServerResponse.createBySuccess(dishList);

	}

	public List<Dish> getDishList(String dName, String sdDate, String edDate){
		List<Dish> dishList  = null;
		if(Strings.isNullOrEmpty(dName)&&Strings.isNullOrEmpty(sdDate)&&Strings.isNullOrEmpty(edDate)){
			String cacheDishList = jedisClient.hget(Const.DISH_CACHE,"alldish");
			if(Strings.isNullOrEmpty(cacheDishList)){
				dishList = dishMapper.selectDishByDNameAndDDate(dName, sdDate,edDate);
				dishCatelogIdToName(dishList);
				cacheDishList = JsonUtils.objectToJson(dishList);
				jedisClient.hset(Const.DISH_CACHE,"alldish",cacheDishList);
				jedisClient.expire(Const.DISH_CACHE, 1000);
			}else{
				dishList = JsonUtils.jsonToList(cacheDishList,Dish.class);
				jedisClient.expire(Const.DISH_CACHE, 1000);
			}
		}
		else {
			if(sdDate != null){
				sdDate = sdDate+" 00:00:00";
				edDate = edDate+" 23:59:59";
			}
			dishList = dishMapper.selectDishByDNameAndDDate(dName, sdDate,edDate);
			dishCatelogIdToName(dishList);
		}
		return dishList;
	}

	public void dishCatelogIdToName(List<Dish> dishList) {
		List<DishCatelog> dishCatelogList = dishCatelogMapper.selectList();
		Map<String,String> map = new HashMap();
		for(DishCatelog dishCatelog : dishCatelogList){
			map.put(dishCatelog.getId().toString(),dishCatelog.getDcName());
		}
		for(Dish dish : dishList){
			dish.setDcId(map.get(dish.getDcId()));
		}
	}
	public void DishUndefinedToNull(Dish dish){

		if(dish.getDcId()!=null&&dish.getDcId().equals("undefined")){
			dish.setDcId(null);
		}
		if(dish.getdName()!=null&&dish.getdName().equals("undefined")) {
			dish.setdName(null);
		}
		if(dish.getdCn()!=null&&dish.getdCn().equals("undefined")) {
			dish.setdCn(null);
		}
		if(dish.getdDate()!=null&&dish.getdDate().equals("undefined")) {
			dish.setdDate(null);
		}
		if(dish.getdMaterial()!=null&&dish.getdMaterial().equals("undefined")) {
			dish.setdMaterial(null);
		}
		if(dish.getdRemark()!=null&&dish.getdRemark().equals("undefined")){
			dish.setdRemark(null);
		}
		if(dish.getDcId()!=null&&dish.getdCount().equals("undefined")){
			dish.setdCount(null);
		}
		if(dish.getdPrice()!=null&&dish.getdPrice().equals("undefined")){
			dish.setdPrice(null);
		}
		if(dish.getdStatus()!=null&&dish.getdStatus().equals("undefined")){
			dish.setdStatus(null);
		}
	}
	public ServerResponse<PageInfo> queryDish(Integer pageNum, Integer pageSize) throws Exception{
		PageHelper.startPage(pageNum,pageSize);
		List<Dish> dishList = dishMapper.selectDish();
		PageInfo pageInfo = new PageInfo(dishList);
		return ServerResponse.createBySuccess(pageInfo);
	}

	public ServerResponse judgeAttribute(BindingResult result) throws Exception{
		StringBuffer sb = new StringBuffer();
		List<FieldError> fieldErrorList = result.getFieldErrors();
		for(FieldError fieldError:fieldErrorList) {
			sb.append(fieldError.getField() + ":" + fieldError.getDefaultMessage() + ";");
		}
		return ServerResponse.createByErrorMessage(sb.toString());
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

