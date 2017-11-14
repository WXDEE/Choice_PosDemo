package com.choice.service;

import com.choice.common.ServerResponse;
import com.choice.dto.OrdersDTO;
import com.choice.entity.Orders;
import com.github.pagehelper.PageInfo;
import com.mysql.fabric.Server;

import java.util.List;

public interface OrdersService {
    //新增订单
    ServerResponse<OrdersDTO> addOrders(OrdersDTO ordersDTO);
    //查询订单(分页)
    ServerResponse<PageInfo<Orders>> queryOrders(Integer pageNum,Integer pageSize);
    //根据订单编号和下单时间查询订单（若条件为空不参与查询，分页）
    ServerResponse<PageInfo<Orders>> queryOrdersByNumAndDate(String oNum,String sDate,String eDate,Integer pageNum,Integer pageSize);
    //查询订单数量
    ServerResponse<String> queryOrdersCount();
    //查询总营业额
    ServerResponse<String> querySumTotal();
    //根据订单id结账
    ServerResponse settleAccount(String id);
}
