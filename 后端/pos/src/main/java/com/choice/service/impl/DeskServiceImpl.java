package com.choice.service.impl;

import com.choice.common.ServerResponse;
import com.choice.entity.Desk;
import com.choice.mapper.DeskMapper;
import com.choice.service.DeskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeskServiceImpl implements DeskService {
	@Autowired
	private DeskMapper deskMapper;
	
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
    }

