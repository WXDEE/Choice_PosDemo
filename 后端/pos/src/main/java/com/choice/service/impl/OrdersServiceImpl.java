package com.choice.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.choice.common.ServerResponse;
import com.choice.dto.OrdersDTO;
import com.choice.entity.OrderItem;
import com.choice.entity.Orders;
import com.choice.mapper.OrderItemMapper;
import com.choice.mapper.OrdersMapper;
import com.choice.service.OrdersService;
import com.choice.util.DateTimeUtil;
import com.choice.util.IDUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
@Service
public class OrdersServiceImpl implements OrdersService {
	@Autowired
	private OrdersMapper ordersMapper;
	@Autowired
	private OrderItemMapper orderItemMapper;
	
	@Transactional
    public ServerResponse<OrdersDTO> addOrders(OrdersDTO ordersDTO) {
    	try {
    		String num = IDUtils.genItemId() + "";
    		String date = DateTimeUtil.dateToStr(new Date());
			Orders orders = new Orders(null, num, date, ordersDTO.getoStatus(),
					ordersDTO.getDeId(),ordersDTO.getoTotal(), ordersDTO.getOdCount());
			List<OrderItem> orderItemList = ordersDTO.getOrderItemList();
			Integer flag1 = ordersMapper.save(orders);
			Integer flag2 = orderItemMapper.save(orderItemList);
			if (flag1 != 0 && flag2 !=0){
				OrdersDTO resultDate = new OrdersDTO();
				resultDate.setId(orders.getId());
				resultDate.setDeId(orders.getDeId());
				resultDate.setoDate(orders.getoDate());
				resultDate.setOdCount(orders.getOdCount());
				resultDate.setoNum(orders.getoNum());
				resultDate.setoStatus(orders.getoStatus());
				resultDate.setoTotal(orders.getoTotal());
				resultDate.setOrderItemList(orderItemList);
				ServerResponse<OrdersDTO> result = ServerResponse.createBySuccess(resultDate);
				return result;
			}
			throw new Exception();
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
			return ServerResponse.createByError();
		}
    }

    public ServerResponse<PageInfo<Orders>> queryOrders(Integer pageNum, Integer pageSize) {
    	try {
			PageHelper.startPage(pageNum, pageSize);
			List<Orders> orderList = ordersMapper.selectAll();
			PageInfo<Orders> pageInfo = new PageInfo<Orders>(orderList);
			ServerResponse<PageInfo<Orders>> result = ServerResponse.createBySuccess(pageInfo);
			return result;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ServerResponse.createByError();
		}
    }

	public ServerResponse<String> queryOrdersCount() {
		try {
			String OrdersCount=ordersMapper.selectOrdesCount();
			return ServerResponse.createBySuccess(OrdersCount);
		} catch (Exception e) {
			// TODO: handle exception
			return ServerResponse.createByError();
		}

	}

	public ServerResponse<String> querySumTotal() {
		try {
			String SumTotal=ordersMapper.selectOrdersToatal();
			return ServerResponse.createBySuccess(SumTotal);
		} catch (Exception e) {
			// TODO: handle exception
			return ServerResponse.createByError();
		}
	}

	public ServerResponse settleAccount(String id) {
		try {
			Integer sta=ordersMapper.updateOrdersStatus(id);
			if(sta==1){
				return ServerResponse.createBySuccess();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return ServerResponse.createByError();
	}

	@Override
	public ServerResponse<List<Orders>> queryOrdersByNumAndDate(
			String oNum, String sDate, String eDate, Integer pageNum,
			Integer pageSize) {
		// TODO Auto-generated method stub
		try {
			if(sDate==null||eDate==null){
				sDate=null;
				eDate=null;
			}
			List<Orders> orders=ordersMapper.selectAllSearch(oNum, sDate, eDate);
			return ServerResponse.createBySuccess(orders);
		} catch (Exception e) {
			// TODO: handle exception
			return ServerResponse.createByError();
		}

	}
}
