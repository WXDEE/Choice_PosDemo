package com.choice.mapper;

import com.choice.entity.Dish;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DishMapper {
    //根据菜品分类id查询该分类下的全部菜品(特价菜品可能要单独写查询方法)
    List<Dish> selectDishByCatelog(String dcId);
    //新增菜品
    Integer insertDish(Dish dish);
    //修改菜品信息(单价/余量/所属类别/原料/备注)
    Integer updateDish(Dish dish);
    //删除菜品
    Integer deleteDishById(Integer id);
    //根据菜品汉字首字母模糊查询
    List<Dish> selectDishByCn(String dCn);
    //查询全部菜品(根据上架时间降序排序)
    List<Dish> selectDish();

    List<Dish> selectDishByDNameAndDDate(@Param("dName") String dName,
                                         @Param("sdDate") String sdDate,
                                         @Param("edDate") String edDate);

    //查询已售空菜品明细
    List<Dish> selectEmptyDish();
    //查询余量不足菜品数量
    Integer selectNotEnoughCount(String num);
    //查询已售空菜品数量
    Integer selectEmptyCount();
    //查询所有菜品数量
    Integer selectDishCount();

}
