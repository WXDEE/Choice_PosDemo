package com.choice.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.choice.common.ServerResponse;
import com.choice.entity.Dish;
import com.choice.mapper.DishMapper;
import com.choice.service.DishService;

@Controller
@RequestMapping("/dish")
public class DishController {
	@Autowired
	private DishService dishService;

	@RequestMapping(value = "{catelog}")
	@ResponseBody
	public ServerResponse queryDishByCatelog(@PathVariable String catelog) {
		return dishService.queryDishByCatelog(catelog);
	}
	@RequestMapping("add")
	@ResponseBody
	public ServerResponse addDish(Dish dish){
		return dishService.addDish(dish);
	}
	@RequestMapping("update")
	@ResponseBody
	public ServerResponse updateDish(Dish dish){
		return dishService.updateDish(dish);
	}

	@RequestMapping("{id}/delete")
	@ResponseBody
	public ServerResponse deleteDish(@PathVariable Integer id){
		return dishService.deleteDish(id);
	}

	@RequestMapping("{cn}")
	@ResponseBody
	public ServerResponse<List<Dish>> queryDishByCn(@PathVariable String cn){
		return dishService.queryDishByCn(cn);
	}
	@RequestMapping("list")
	@ResponseBody
	public ServerResponse queryDishByNameAndDate(String dName, String sdDate, String edDate, Integer pageNum, Integer pageSize){
		return dishService.queryDishByNameAndDate(dName,sdDate,edDate,pageNum,pageSize);
	}
	/*@RequestMapping("/")
	@ResponseBody
	public ServerResponse<PageInfo> queryDish(Integer pageNum,Integer pageSize){
		return dishService.queryDish(pageNum,pageSize);
	}*/
	@RequestMapping("none/count")
	@ResponseBody
	public ServerResponse<String> queryDishCountWithNone(){
		ServerResponse<String> result = dishService.queryDishCountWithNone();
		return result;
	}
	@RequestMapping("notEnough/count")
	@ResponseBody
	public ServerResponse<String> queryDishCountithNotEnough(){
		ServerResponse<String> result = dishService.queryDishCountithNotEnough();
		return result;
	}
	@RequestMapping("count")
	@ResponseBody
	public ServerResponse<String> queryDishCount(){
		ServerResponse<String> result = dishService.queryDishCount();
		return result;
	}
	@RequestMapping("none/list")
	@ResponseBody
	public ServerResponse<List<Dish>> queryDishWithNone(){
		ServerResponse<List<Dish>> result = dishService.queryDishWithNone();
		return result;
	}
	
}
