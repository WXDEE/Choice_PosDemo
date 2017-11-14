package com.choice.service.impl;

import com.choice.common.Const;
import com.choice.common.ServerResponse;
import com.choice.entity.Dish;
import com.choice.mapper.DishMapper;
import com.choice.service.DishService;
import com.github.pagehelper.PageInfo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DishServiceImpl implements DishService {
	@Autowired
	private DishMapper dishMapper;
	
    public ServerResponse<List<Dish>> queryDishByCatelog(String catelog) {
        return null;
    }

    public ServerResponse addDish(Dish dish) {
        return null;
    }

    public ServerResponse updateDish(Dish dish) {
        return null;
    }

    public ServerResponse deleteDish(String id) {
        return null;
    }

    public ServerResponse<List<Dish>> queryDishByCn(String cn) {
        return null;
    }

    public ServerResponse<PageInfo<List<Dish>>> queryDishByNameAndDate(String dName, String sdDate, String edDate, Integer pageNum, Integer pageSize) {
        return null;
    }

    public ServerResponse<PageInfo<List<Dish>>> queryDish(Integer pageNum, Integer pageSize) {
        return null;
    }

    public ServerResponse<List<Dish>> queryDishWithNotEnough() {
        return null;
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
			System.out.println(count);
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
