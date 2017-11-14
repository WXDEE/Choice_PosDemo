package com.choice.mapper;

import java.util.List;

import com.choice.entity.Orders;
import org.apache.ibatis.annotations.Param;

public interface OrdersMapper {
	//保存订单
	Integer save(Orders orders);
	//查询全部订单
	List<Orders> selectAll();
	//根据订单编号和下单时间查询订单(空条件不参与查询)
	List<Orders> selectAllSearch(@Param("oNum") String oNum,
						   @Param("bDate") String bDate,
						   @Param("eDate") String eDate);
	//查询订单数
	String selectOrdesCount();
	//查询营业额
	String selectOrdersToatal();
	//点击结账，修改订单状态为已付款
	Integer updateOrdersStatus(String id);

}
