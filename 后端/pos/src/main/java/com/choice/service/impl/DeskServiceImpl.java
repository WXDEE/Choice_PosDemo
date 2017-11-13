package com.choice.service.impl;

import com.choice.common.ServerResponse;
import com.choice.entity.Desk;
import com.choice.mapper.DeskMapper;
import com.choice.service.DeskService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class DeskServiceImpl implements DeskService {
	@Autowired
	private DeskMapper deskMapper;
	
    public ServerResponse<List<Desk>> queryAllDesk() {
        return null;
    }

    public ServerResponse<Desk> queryDeskByNum(String num) {
        return null;
    }
}
