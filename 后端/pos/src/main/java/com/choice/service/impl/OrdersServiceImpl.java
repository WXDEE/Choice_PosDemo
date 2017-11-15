package com.choice.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.choice.common.ServerResponse;
import com.choice.dto.OrdersDTO;
import com.choice.entity.Desk;
import com.choice.entity.OrderItem;
import com.choice.entity.Orders;
import com.choice.mapper.DeskMapper;
import com.choice.mapper.OrderItemMapper;
import com.choice.mapper.OrdersMapper;
import com.choice.service.OrdersService;
import com.choice.util.DateTimeUtil;
import com.choice.util.IDUtils;
import com.choice.util.JsonUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
@Service
public class OrdersServiceImpl implements OrdersService {
	@Autowired
	private OrdersMapper ordersMapper;
	@Autowired
	private DeskMapper deskMapper;
	@Autowired
	private OrderItemMapper orderItemMapper;
	@Resource(name = "jmsTemplate")
	private JmsTemplate jmsTemplate;
	
	@Transactional
    public ServerResponse<OrdersDTO> addOrders(OrdersDTO ordersDTO) {
    	try {
    		String num = IDUtils.genItemId() + "";
    		String date = DateTimeUtil.dateToStr(new Date());
			Orders orders = new Orders(null, num, date, "0",
					ordersDTO.getDeId(),ordersDTO.getoTotal(), ordersDTO.getOdCount());
			List<OrderItem> orderItemList = ordersDTO.getOrderItemList();
			Integer flag1 = ordersMapper.save(orders);
			for (OrderItem orderItem : orderItemList) {
				orderItem.setoId(orders.getId()+"");
				orderItem.setOiStatus("0");
			}
			Integer flag2 = orderItemMapper.save(orderItemList);
			if (flag1 != 0 && flag2 !=0){
				OrdersDTO resultDate = new OrdersDTO();
				resultDate.setId(orders.getId());
				resultDate.setDeId(orders.getDeId());
				resultDate.setoDate(orders.getoDate());
				resultDate.setOdCount(orders.getOdCount());
				resultDate.setoNum(orders.getoNum());
				resultDate.setoStatus("已下单");
				resultDate.setoTotal(orders.getoTotal());
				resultDate.setOrderItemList(orderItemList);
				ServerResponse<OrdersDTO> result = ServerResponse.createBySuccess(resultDate);
				String destination = jmsTemplate.getDefaultDestinationName();
		        System.out.println("订单生成");
		        jmsTemplate.send(new MessageCreator() {
		            public Message createMessage(Session session) throws JMSException {
		                return session.createTextMessage("订单生成:"+JsonUtils.objectToJson(resultDate));
		            }
		        });
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
	@Transactional
	public ServerResponse settleAccount(String id,String deNum) {
		try {
			Integer sta=ordersMapper.updateOrdersStatus(id);
			Integer stb=deskMapper.updateDeskStatusByNum(deNum, "0");
			if(sta==1&&stb==1){
				return ServerResponse.createBySuccess();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return ServerResponse.createByError();
	}

	@Override
	public ServerResponse<List<Orders>> queryOrdersByNumAndDate(String oNum, String sDate, String eDate) {
		// TODO Auto-generated method stub
		try {
			if(StringUtils.isBlank(sDate)||StringUtils.isBlank(sDate)){
				sDate=null;
				eDate=null;
			}
			List<Orders> orders=ordersMapper.selectAllSearch(oNum, sDate, eDate);
			List<Desk> desks=deskMapper.selectAllDesk();
			Map<String, String> map=new HashMap<String, String>();
			for (Desk desk : desks) {
				map.put(desk.getId().toString(), desk.getDeNum());
			}
			for (Orders orders2 : orders) {
				orders2.setDeId(map.get(orders2.getDeId()));
				switch(orders2.getoStatus()){
				 case "0":orders2.setoStatus("已下单 ");break;
				 case "1":orders2.setoStatus("代付款 ");break;
				 case "2":orders2.setoStatus("已结账 ");break;
				 default :break;
				}
			}
			
			return ServerResponse.createBySuccess(orders);
		} catch (Exception e) {
			// TODO: handle exception
			return ServerResponse.createByError();
		}

	}
}
