package com.choice.service.impl;

import com.choice.common.Const;
import com.choice.common.ServerResponse;
import com.choice.entity.Desk;
import com.choice.mapper.DeskMapper;
import com.choice.mapper.JedisClient;
import com.choice.service.DeskService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeskServiceImpl implements DeskService {
	@Autowired
	private DeskMapper deskMapper;
	
	@Autowired
	JedisClient jedisClient;
	
    public ServerResponse<List<Desk>> queryAllDesk() {
    	try {
    		List<Desk> desks=deskMapper.selectAllDesk();
    		 return ServerResponse.createBySuccess(desks);
		} catch (Exception e) {
			// TODO: handle exception
			return ServerResponse.createByError();
		}      
    }

    public ServerResponse<Desk> queryDeskByNum(String num) {
    	try {
    		Desk desk=deskMapper.selectDeskById(num);
    		 return ServerResponse.createBySuccess(desk);
		} catch (Exception e) {
			// TODO: handle exception
			return ServerResponse.createByError();
		}    
    }

	@Override
	public ServerResponse updateDeskStatusByNum(String deNum,String status) {
		// TODO Auto-generated method stub
		try {
			Integer success=deskMapper.updateDeskStatusByNum(deNum, status);
			if(success==1){
				return ServerResponse.createBySuccess();
			}
		} catch (Exception e) {
			// TODO: handle exception
			return ServerResponse.createByError();
		}
		return null;
		
	}
    }

