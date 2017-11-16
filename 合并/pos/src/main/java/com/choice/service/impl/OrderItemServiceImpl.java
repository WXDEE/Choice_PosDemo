package com.choice.service.impl;

import com.choice.common.Const;
import com.choice.common.ServerResponse;
import com.choice.dto.OrdersDTO;
import com.choice.entity.Desk;
import com.choice.entity.Dish;
import com.choice.entity.OrderItem;
import com.choice.mapper.JedisClient;
import com.choice.mapper.OrderItemMapper;
import com.choice.service.DeskService;
import com.choice.service.DishService;
import com.choice.service.OrderItemService;
import com.choice.service.OrdersService;
import com.choice.util.JsonUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class OrderItemServiceImpl implements OrderItemService {
	@Autowired
	private OrdersService ordersService;
	@Autowired
	private OrderItemMapper orderItemMapper;
	@Autowired
	private DishService dishService;
	@Autowired
	private JedisClient jedisClient;
	/***
	 * 通过订单id查询订单明细
	 */
	@Transactional
    public ServerResponse<OrdersDTO> queryOrderItemByOrdersId(String ordersId) throws Exception{
		OrdersDTO ordersDTO = new OrdersDTO();
		//取dish列表
		List<Dish> dishList = queryAllDish();
		Map<String, String> map = new HashMap<String, String>();
		//将菜品id，菜品名称封装进map
		List<OrderItem> orderItemList = orderItemMapper.selectByOid(ordersId);
		for (Dish dish : dishList) {
			map.put(dish.getId().toString(), dish.getdName());
		}
		//将订单明细中菜品id替换为名称，将状态改为文字
		for (OrderItem orderItem : orderItemList) {
			orderItem.setdId(map.get(orderItem.getdId()));
			orderItem.setOiStatus(orderItem.getOiStatus().equals("0")?"未上菜":"已上菜");
		}
		//取订单表中的订单号，桌子号，下单时间，总金额
		Map<String, String> orderMap = ordersService.selectToItem(ordersId);
		//封装进ordersdto
		while(orderMap != null && orderMap.size() > 0){
			ordersDTO.setoNum(orderMap.get("o_num"));
			ordersDTO.setDeId(orderMap.get("de_id"));
			ordersDTO.setoDate(orderMap.get("o_date"));
			ordersDTO.setoTotal(orderMap.get("o_total"));
			ordersDTO.setOrderItemList(orderItemList);
			ServerResponse<OrdersDTO> result = ServerResponse.createBySuccess(ordersDTO);
			return result;
		}
		throw new Exception();
    }

	/**
	 * 上菜   根据订单详情的id  来修改菜的状态（是否上菜）
	 */
	@Override
	public ServerResponse upDish(String ordersItemId) {
		Integer sta=orderItemMapper.updateDishStatus(ordersItemId);
		return ServerResponse.createBySuccess();
	}
	/***
	 * 取出所有菜品
	 * @return
	 * @throws Exception
	 */
	public List<Dish> queryAllDish() throws Exception{
		List<Dish> dishList = null;
		//先从redis中获取全部菜品
		String json = jedisClient.hget(Const.DISH_CACHE, "alldish");
		//若redis不为空将json转换为dish列表
		if(!StringUtils.isBlank(json)){
			jedisClient.expire(Const.DISH_CACHE, 1000);
			dishList = JsonUtils.jsonToList(json, Dish.class);	
		}else{
			//若没有，从数据库中取然后转换为json放入redis
			dishList = dishService.queryDishByNameAndDate(null, null, null).getData();
			System.out.println(dishList);
			String dishjson = JsonUtils.objectToJson(dishList);
			jedisClient.hset(Const.DISH_CACHE, "alldish",dishjson);
			jedisClient.expire(Const.DISH_CACHE, 1000);
		}
		//返回dish列表
		return dishList;
	}
}
