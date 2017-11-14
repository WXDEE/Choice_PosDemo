package com.choice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.choice.common.ServerResponse;
import com.choice.entity.OrderItem;
import com.choice.service.OrderItemService;

@Controller
@RequestMapping("/orderitem")
public class OrderItemController {
	@Autowired
	private OrderItemService orderItemService;
	
	@RequestMapping("ordersId")
	@ResponseBody
	public ServerResponse<List<OrderItem>> queryOrderItemByOrdersId(@PathVariable String ordersId){
		ServerResponse<List<OrderItem>> result = orderItemService.queryOrderItemByOrdersId(ordersId);
		return result;
	}

	@RequestMapping("updish")
	public ServerResponse upDish(@PathVariable String ordersItemId) {
		return orderItemService.upDish(ordersItemId);
	}
	
}
