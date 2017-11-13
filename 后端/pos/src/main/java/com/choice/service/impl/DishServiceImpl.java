package com.choice.service.impl;

import com.choice.common.ServerResponse;
import com.choice.entity.Dish;
import com.choice.service.DishService;
import com.github.pagehelper.PageInfo;

import java.util.List;

public class DishServiceImpl implements DishService {
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
        return null;
    }

    public ServerResponse<String> queryDishCountWithNone() {
        return null;
    }

    public ServerResponse<String> queryDishCountithNotEnough() {
        return null;
    }
}
