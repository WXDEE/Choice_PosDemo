package com.choice.controller;

import java.util.List;

import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.choice.common.ServerResponse;
import com.choice.entity.Dish;
import com.choice.service.DishService;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/dish")
public class DishController {
	@Autowired
	private DishService dishService;


	@RequestMapping(value = "catelog")
	@ResponseBody
	public ServerResponse queryDishByCatelog(String catelog,HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		return dishService.queryDishByCatelog(catelog);
	}
	@RequestMapping(value = "add")
	@ResponseBody
	public ServerResponse addDish(Dish dish,HttpServletResponse response){
		response.setHeader("Access-Control-Allow-Origin", "*");
		return dishService.addDish(dish);
	}
	@RequestMapping("update")
	@ResponseBody
	public ServerResponse updateDish(Dish dish,HttpServletResponse response){
		response.setHeader("Access-Control-Allow-Origin", "*");
		return dishService.updateDish(dish);
	}

	@RequestMapping("delete")
	@ResponseBody
	public ServerResponse deleteDish(Integer id,HttpServletResponse response){
		response.setHeader("Access-Control-Allow-Origin", "*");
		return dishService.deleteDish(id);
	}

	@RequestMapping("cn")
	@ResponseBody
	public ServerResponse<List<Dish>> queryDishByCn(String cn,HttpServletResponse response){
		response.setHeader("Access-Control-Allow-Origin", "*");
		return dishService.queryDishByCn(cn);
	}
	@RequestMapping("list")
	@ResponseBody
	public ServerResponse queryDishByNameAndDate(String dName, String sdDate, String edDate,HttpServletResponse response){
		response.setHeader("Access-Control-Allow-Origin", "*");
		return dishService.queryDishByNameAndDate(dName,sdDate,edDate);
	}
	/*@RequestMapping("/")
	@ResponseBody
	public ServerResponse<PageInfo> queryDish(Integer pageNum,Integer pageSize){
		return dishService.queryDish(pageNum,pageSize);
	}*/
	/***
	 * 查询售空菜品数量
	 * @param response
	 * @return
	 */
	@RequestMapping("none/count")
	@ResponseBody
	public ServerResponse<String> queryDishCountWithNone(HttpServletResponse response){
		response.setHeader("Access-Control-Allow-Origin", "*");
		ServerResponse<String> result = dishService.queryDishCountWithNone();
		return result;
	}
	/**
	 * 查询菜品不足的数量
	 * @param response
	 * @return
	 */
	@RequestMapping("notEnough/count")
	@ResponseBody
	public ServerResponse<String> queryDishCountithNotEnough(HttpServletResponse response){
		response.setHeader("Access-Control-Allow-Origin", "*");
		ServerResponse<String> result = dishService.queryDishCountithNotEnough();
		return result;
	}
	/***
	 * 查询菜品总数量
	 * @param response
	 * @return
	 */
	@RequestMapping("count")
	@ResponseBody
	public ServerResponse<String> queryDishCount(HttpServletResponse response){
		response.setHeader("Access-Control-Allow-Origin", "*");
		ServerResponse<String> result = dishService.queryDishCount();
		return result;
	}
	/***
	 * 查询售空菜品列表
	 * @param response
	 * @return
	 */
	@RequestMapping("none/list")
	@ResponseBody
	public ServerResponse<List<Dish>> queryDishWithNone(HttpServletResponse response){
		response.setHeader("Access-Control-Allow-Origin", "*");
		ServerResponse<List<Dish>> result = dishService.queryDishWithNone();
		return result;
	}
	
}
