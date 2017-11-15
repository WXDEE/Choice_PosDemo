package com.choice.service.impl;

import com.choice.common.Const;
import com.choice.common.ServerResponse;
import com.choice.entity.Desk;
import com.choice.entity.Dish;
import com.choice.entity.OrderItem;
import com.choice.mapper.JedisClient;
import com.choice.mapper.OrderItemMapper;
import com.choice.service.DeskService;
import com.choice.service.DishService;
import com.choice.service.OrderItemService;
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
	private OrderItemMapper orderItemMapper;
	@Autowired
	private DishService dishService;
	@Autowired
	private JedisClient jedisClient;
	@Transactional
    public ServerResponse<List<OrderItem>> queryOrderItemByOrdersId(String ordersId) {
    	try {
    		List<Dish> dishList = null;
    		//取dish列表
    		try {
				String json = jedisClient.hget(Const.DISH_CACHE, "alldish");
				if(!StringUtils.isBlank(json)){
					jedisClient.expire(Const.DISH_CACHE, 1000);
					dishList = JsonUtils.jsonToList(json, Dish.class);	
				}else{
					dishList = dishService.queryDishByNameAndDate(null, null, null).getData();
					System.out.println(dishList);
					String dishjson = JsonUtils.objectToJson(dishList);
					jedisClient.hset(Const.DISH_CACHE, "alldish",dishjson);
					jedisClient.expire(Const.DISH_CACHE, 1000);
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
    		Map<String, String> map = new HashMap<String, String>();
			List<OrderItem> orderItemList = orderItemMapper.selectByOid(ordersId);
			for (Dish dish : dishList) {
				map.put(dish.getId().toString(), dish.getdName());
			}
			for (OrderItem orderItem : orderItemList) {
				orderItem.setdId(map.get(orderItem.getdId()));
			}
			ServerResponse<List<OrderItem>> result = ServerResponse.createBySuccess(orderItemList);
			return result;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ServerResponse.createByError();
		}
    }

	@Override
	public ServerResponse upDish(String ordersItemId) {
		// TODO Auto-generated method stub

		try {
			Integer sta=orderItemMapper.updateDishStatus(ordersItemId);
			if(sta==1){
				return ServerResponse.createBySuccess();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return ServerResponse.createByError();
	}
}
