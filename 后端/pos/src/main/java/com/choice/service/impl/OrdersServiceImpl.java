package com.choice.service.impl;

import com.choice.common.ServerResponse;
import com.choice.dto.OrdersDTO;
import com.choice.entity.Orders;
import com.choice.service.OrdersService;
import com.github.pagehelper.PageInfo;

import java.util.List;

public class OrdersServiceImpl implements OrdersService {
    public ServerResponse<OrdersDTO> addOrders(OrdersDTO ordersDTO) {
        return null;
    }

    public ServerResponse<PageInfo<List<Orders>>> queryOrders(Integer pageNum, Integer pageSize) {
        return null;
    }

    public ServerResponse<PageInfo<List<Orders>>> queryOrdersByNumAndDate(Integer oNum, Integer oDate, Integer pageNum, Integer pageSize) {
        return null;
    }

    public ServerResponse<String> queryOrdersCount() {
        return null;
    }

    public ServerResponse<String> querySumTotal() {
        return null;
    }

    public ServerResponse settleAccount(String id) {
        return null;
    }

    public ServerResponse upDish(String ordersItemId) {
        return null;
    }
}
