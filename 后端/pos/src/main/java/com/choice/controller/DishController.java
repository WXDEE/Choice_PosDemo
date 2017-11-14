package com.choice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.choice.common.ServerResponse;
import com.choice.entity.Dish;
import com.choice.mapper.DishMapper;
import com.choice.service.DishService;

@Controller
@RequestMapping("dish")
public class DishController {
	@Autowired
	private DishService dishService;
	
	@RequestMapping("/query1")
	@ResponseBody
	public ServerResponse<String> queryDishCountWithNone(){
		ServerResponse<String> result = dishService.queryDishCountWithNone();
		return result;
	}
	@RequestMapping("/query2")
	@ResponseBody
	public ServerResponse<String> queryDishCountithNotEnough(){
		ServerResponse<String> result = dishService.queryDishCountithNotEnough();
		return result;
	}
	@RequestMapping("/query3")
	@ResponseBody
	public ServerResponse<String> queryDishCount(){
		ServerResponse<String> result = dishService.queryDishCount();
		return result;
	}
	@RequestMapping("/query4")
	@ResponseBody
	public ServerResponse<List<Dish>> queryDishWithNone(){
		ServerResponse<List<Dish>> result = dishService.queryDishWithNone();
		return result;
	}
	
}
