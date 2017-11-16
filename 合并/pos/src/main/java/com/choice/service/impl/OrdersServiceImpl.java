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
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.choice.common.ServerResponse;
import com.choice.dto.OrdersDTO;
import com.choice.entity.Desk;
import com.choice.entity.OrderItem;
import com.choice.entity.Orders;
import com.choice.mapper.DeskMapper;
import com.choice.mapper.OrderItemMapper;
import com.choice.mapper.OrdersMapper;
import com.choice.service.MQService;
import com.choice.service.OrdersService;
import com.choice.util.DateTimeUtil;
import com.choice.util.IDUtils;
import com.choice.util.JsonUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
@Service
public class OrdersServiceImpl implements OrdersService {
	@Autowired
	private ThreadPoolTaskExecutor taskExecutor;
	@Autowired
	private MQService mQService;
	@Autowired
	private OrdersMapper ordersMapper;
	@Autowired
	private DeskMapper deskMapper;
	@Autowired
	private OrderItemMapper orderItemMapper;
	@Resource(name = "jmsTemplate")
	private JmsTemplate jmsTemplate;
	
	@Transactional
    public ServerResponse<OrdersDTO> addOrders(String data) {
    	try {
    		OrdersDTO ordersDTO = JsonUtils.jsonToPojo(data, OrdersDTO.class);
    		Orders orders = insertOrders(ordersDTO);
			List<OrderItem> orderItemList = ordersDTO.getOrderItemList();
			orderItemList = insertOrderItem(orderItemList,orders);
			ordersDTO.setId(orders.getId());
			ordersDTO.setoDate(orders.getoDate());
			ordersDTO.setoNum(orders.getoNum());
			ordersDTO.setoStatus("已下单");
			ordersDTO.setOrderItemList(orderItemList);
			ServerResponse<OrdersDTO> result = ServerResponse.createBySuccess(ordersDTO);
	        System.out.println("订单生成");
	        printOrder(ordersDTO);
	        deskMapper.updateDeskStatusByNum(ordersDTO.getDeId(), "1");
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly(); 
			return ServerResponse.createByErrorMessage("请选择菜品！！！");
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
			if("undefined".equals(sDate)||"undefined".equals(eDate)){
				sDate=null;
				eDate=null;
			}
			if(sDate!=null){
				sDate = sDate + " 00:00:00";
				eDate = eDate + " 23:59:59";
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

	@Override
	public Map<String, String> selectToItem(String id) {
		// TODO Auto-generated method stub
		try {
			Map<String, String> map = ordersMapper.selectToItem(id);
			return map;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	//插入订单表
	@Transactional
	public Orders insertOrders(OrdersDTO ordersDTO) throws Exception{
		String num = IDUtils.genItemId() + "";
		String date = DateTimeUtil.dateToStr(new Date());
		Orders orders = new Orders(null, num, date, "0",
				ordersDTO.getDeId(),ordersDTO.getoTotal(), ordersDTO.getOdCount());
		Integer flag = ordersMapper.save(orders);
		if(flag != 0){
			return orders;
		}
		throw new Exception();
	}
	//插入订单明细表
	@Transactional
	public List<OrderItem> insertOrderItem(List<OrderItem> orderItemList,Orders orders) throws Exception{
		for (OrderItem orderItem : orderItemList) {
			orderItem.setoId(orders.getId()+"");
			orderItem.setOiStatus("0");
		}
		Integer flag = orderItemMapper.save(orderItemList);
		if(flag != 0){
			return orderItemList;
		}
		throw new Exception();
	
	}
	//异步打印订单
	public void printOrder(OrdersDTO ordersDTO){
		taskExecutor.execute(new Runnable() {
			@Override
			public void run() {
				mQService.sendMessage(JsonUtils.objectToJson(ordersDTO));
			}
		});
	}
}
