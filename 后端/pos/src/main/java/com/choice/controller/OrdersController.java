package com.choice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.choice.common.ServerResponse;
import com.choice.dto.OrdersDTO;
import com.choice.entity.Orders;
import com.choice.service.OrdersService;
import com.github.pagehelper.PageInfo;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrdersController {
	@Autowired
	private OrdersService ordersService;
	
	@RequestMapping("add")
	@ResponseBody
	public ServerResponse<OrdersDTO> addOrders(OrdersDTO ordersDTO){
		ServerResponse<OrdersDTO> result = ordersService.addOrders(ordersDTO);
		return result;
	}
	
	/*@RequestMapping("/list")
	@ResponseBody
	public ServerResponse<PageInfo<Orders>> queryOrders(Integer pageNum,Integer pageSize){
		ServerResponse<PageInfo<Orders>> result = ordersService.queryOrders(pageNum, pageSize);
		return result;
	}*/

	//根据订单编号和下单时间查询订单（若条件为空不参与查询，分页）
	@RequestMapping("list")
	@ResponseBody
	public ServerResponse<List<Orders>> queryOrdersByNumAndDate(String oNum, String sDate, String eDate){
		return ordersService.queryOrdersByNumAndDate(oNum, sDate, eDate);
	}
	//查询订单数量
	@RequestMapping("count")
	@ResponseBody
	public  ServerResponse<String> queryOrdersCount(){
		return ordersService.queryOrdersCount();
	}
	//查询总营业额
	@RequestMapping("sumTotal")
	@ResponseBody
	public  ServerResponse<String> querySumTotal(){
		return ordersService.querySumTotal();
	}
	//根据订单id结账
	@RequestMapping("settleAccount")
	@ResponseBody
	public  ServerResponse settleAccount(String id){
		return ordersService.settleAccount(id);
	}
}
