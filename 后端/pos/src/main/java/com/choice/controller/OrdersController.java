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

@Controller
@RequestMapping("orders")
public class OrdersController {
	@Autowired
	private OrdersService ordersService;
	
	@RequestMapping("/add")
	@ResponseBody
	public ServerResponse<OrdersDTO> addOrders(OrdersDTO ordersDTO){
		ServerResponse<OrdersDTO> result = ordersService.addOrders(ordersDTO);
		return result;
	}
	
	@RequestMapping("/query")
	@ResponseBody
	public ServerResponse<PageInfo<Orders>> queryOrders(Integer pageNum,Integer pageSize){
		ServerResponse<PageInfo<Orders>> result = ordersService.queryOrders(pageNum, pageSize);
		return result;
	}
}
