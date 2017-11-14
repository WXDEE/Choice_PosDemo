package com.choice.service.impl;

import com.choice.common.Const;
import com.choice.common.ServerResponse;
import com.choice.entity.Dish;
import com.choice.mapper.DishMapper;
import com.choice.service.DishService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DishServiceImpl implements DishService {
	@Autowired
	private DishMapper dishMapper;
	
    public ServerResponse<List<Dish>> queryDishByCatelog(String catelog) {
		try {
			List<Dish> dishList = dishMapper.selectDishByCatelog(catelog);
			return ServerResponse.createBySuccess(dishList);
		} catch (Exception e) {
			return ServerResponse.createByError();
		}
    }

    public ServerResponse addDish(Dish dish) {
		try {
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
			return ServerResponse.createBySuccess(dishList);
		} catch (Exception e) {
			return ServerResponse.createByError();
		}


	}

    public ServerResponse<List<Dish>> queryDishByNameAndDate(String dName, String sdDate, String edDate) {
		try {
			if(sdDate==null||edDate==null){
				sdDate = null;
				edDate = null;
			}
			List<Dish> dishList = dishMapper.selectDishByDNameAndDDate(dName, sdDate,edDate);
			return ServerResponse.createBySuccess(dishList);
		} catch (Exception e) {
			e.printStackTrace();
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
