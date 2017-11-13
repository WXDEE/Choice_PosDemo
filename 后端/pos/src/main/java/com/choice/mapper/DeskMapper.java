package com.choice.mapper;

import java.util.List;

import com.choice.entity.Desk;

public interface DeskMapper {
	//查询全部桌号方法
	List<Desk> selectAllDesk();
	//根据桌号查询桌子信息
	Desk selectDeskById(String id);
	
}
