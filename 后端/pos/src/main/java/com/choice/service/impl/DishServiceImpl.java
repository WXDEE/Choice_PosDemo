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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DishServiceImpl implements DishService {
	@Autowired
	private DishMapper dishMapper;
	@Autowired
	private DishCatelogMapper dishCatelogMapper;

	@Autowired
	private JedisClient jedisClient;
	public ServerResponse<List<Dish>> queryDishByCatelog(String catelog) {
		try {
			try {
				String json = jedisClient.hget(Const.DISH_CACHE, catelog);
				if(!StringUtils.isBlank(json)){
					jedisClient.expire(Const.DISH_CACHE, 1000);
					List<Dish> dishList = JsonUtils.jsonToList(json, Dish.class);
					return ServerResponse.createBySuccess(dishList);
				}
			} catch (Exception e) {
				// TODO: handle exception
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
			try {
				String json = JsonUtils.objectToJson(dishList);
				jedisClient.hset(Const.DISH_CACHE, catelog, json);
				jedisClient.expire(Const.DISH_CACHE, 1000);
			} catch (Exception e) {
				// TODO: handle exception
			}
			return ServerResponse.createBySuccess(dishList);
		} catch (Exception e) {
			return ServerResponse.createByError();
		}
	}

	public ServerResponse addDish(Dish dish) {
		try {
			String date = DateTimeUtil.dateToStr(new Date(),"yyyy-MM-dd HH:mm:ss");
			dish.setdDate(date);
			Integer result = dishMapper.insertDish(dish);
			if(result.equals(1)){
				return ServerResponse.createBySuccess();
			}else {
				return ServerResponse.createByError();
			}
		} catch (Exception e) {
			return ServerResponse.createByError();
		}
	}

	public ServerResponse updateDish(Dish dish) {
		try {
			Integer result = dishMapper.updateDish(dish);
			if(result.equals(1)){
				return ServerResponse.createBySuccess();
			}else {
				return ServerResponse.createByError();
			}
		} catch (Exception e) {
			return ServerResponse.createByError();
		}
	}

	public ServerResponse deleteDish(Integer id) {
		try {
			Integer result = dishMapper.deleteDishById(id);
			if(result.equals(1)){
				return ServerResponse.createBySuccess();
			}else {
				return ServerResponse.createByError();
			}
		} catch (Exception e) {
			return ServerResponse.createByError();
		}
	}

	public ServerResponse<List<Dish>> queryDishByCn(String cn) {
		try {
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
		} catch (Exception e) {
			return ServerResponse.createByError();
		}


	}

	public ServerResponse<List<Dish>> queryDishByNameAndDate(String dName, String sdDate, String edDate) {

		try {
			if(sdDate==null|| StringUtils.isBlank(sdDate)||edDate==null||StringUtils.isBlank(edDate)){
				sdDate = null;
				edDate = null;
			}
			else {
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
		} catch (Exception e) {
			return ServerResponse.createByError();
		}

	}

	public ServerResponse<PageInfo> queryDish(Integer pageNum, Integer pageSize) {
		try {
			PageHelper.startPage(pageNum,pageSize);
			List<Dish> dishList = dishMapper.selectDish();
			PageInfo pageInfo = new PageInfo(dishList);
			return ServerResponse.createBySuccess(pageInfo);
		} catch (Exception e) {
			return ServerResponse.createByError();
		}
	}

	public ServerResponse<List<Dish>> queryDishWithNone() {
		try{
			List<Dish> emptyDishList = dishMapper.selectEmptyDish();
			ServerResponse<List<Dish>> result = ServerResponse.createBySuccess(emptyDishList);
			return result;
		} catch (Exception e){
			e.printStackTrace();
			return ServerResponse.createByError();
		}
	}

	public ServerResponse<String> queryDishCountWithNone() {
		try {
			Integer count = dishMapper.selectEmptyCount();
			ServerResponse<String> result = ServerResponse.createBySuccess(""+count);
			return result;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ServerResponse.createByError();
		}
	}

	public ServerResponse<String> queryDishCountithNotEnough() {
		try {
			Integer count = dishMapper.selectNotEnoughCount(Const.DHSI_NUM);
			ServerResponse<String> result = ServerResponse.createBySuccess(""+count);
			return result;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ServerResponse.createByError();
		}
	}

	public ServerResponse<String> queryDishCount() {
		try {
			Integer count = dishMapper.selectDishCount();
			ServerResponse<String> result = ServerResponse.createBySuccess(""+count);
			return result;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ServerResponse.createByError();
		}
	}
}
