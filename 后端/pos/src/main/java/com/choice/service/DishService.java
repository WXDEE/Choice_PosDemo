package com.choice.service;

import com.choice.common.ServerResponse;
import com.choice.entity.Dish;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface DishService {
    //根据菜品种类id查询菜品
    ServerResponse<List<Dish>> queryDishByCatelog(String catelog);
    //新增菜品
    ServerResponse addDish(Dish dish);
    //更新菜品
    ServerResponse updateDish(Dish dish);
    //删除菜品
    ServerResponse deleteDish(Integer id);
    //根据菜品汉拼首字母模糊查询菜品
    ServerResponse<List<Dish>> queryDishByCn(String cn);
    //根据菜品名称和上架日期查询菜品（分页）
    ServerResponse<PageInfo> queryDishByNameAndDate(String dName, String sdDate, String edDate,Integer pageNum,Integer pageSize);
    //查询菜品（分页）
    ServerResponse<PageInfo> queryDish(Integer pageNum,Integer pageSize);
    //查询售空菜品
    ServerResponse<List<Dish>> queryDishWithNone();
    //查询售空菜品数量
    ServerResponse<String> queryDishCountWithNone();
    //查询余量不足菜品数
    ServerResponse<String> queryDishCountithNotEnough();
    //查询菜品种类数
    ServerResponse<String> queryDishCount();
}
