package com.choice.service.impl;

import com.choice.common.ServerResponse;
import com.choice.entity.OrderItem;
import com.choice.mapper.OrderItemMapper;
import com.choice.service.OrderItemService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class OrderItemServiceImpl implements OrderItemService {
	@Autowired
	private OrderItemMapper orderItemMapper;
	
    public ServerResponse<List<OrderItem>> queryOrderItemByOrdersId(String ordersId) {
    	try {
			List<OrderItem> orderItemList = orderItemMapper.selectByOid(ordersId);
			ServerResponse<List<OrderItem>> result = ServerResponse.createBySuccess(orderItemList);
			return result;
		} catch (Exception e) {
			// TODO: handle exception
			return ServerResponse.createByError();
		}
    }
}
