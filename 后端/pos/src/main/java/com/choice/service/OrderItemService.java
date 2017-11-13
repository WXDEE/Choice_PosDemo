package com.choice.service;

import com.choice.common.ServerResponse;
import com.choice.entity.Dish;
import com.choice.entity.OrderItem;

import java.util.List;

public interface OrderItemService {
    //根据订单id查询订单详细
    ServerResponse<List<OrderItem>> queryOrderItemByOrdersId(String ordersId);
}
