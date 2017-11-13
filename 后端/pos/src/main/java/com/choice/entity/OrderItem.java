package com.choice.entity;

public class OrderItem {
	
	private Integer id;
	private String oiNum;//订单明细编号
	private String oNum;//订单编号
	private String dNum;//菜品编号
	private String oiCount;//单一菜品数量
	private String oiStatus;//订单菜品状态 0：未上菜 1：已上菜

	public String getOiStatus() {
		return oiStatus;
	}

	public void setOiStatus(String oiStatus) {
		this.oiStatus = oiStatus;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getOiNum() {
		return oiNum;
	}
	public void setOiNum(String oiNum) {
		this.oiNum = oiNum;
	}
	public String getoNum() {
		return oNum;
	}
	public void setoNum(String oNum) {
		this.oNum = oNum;
	}
	public String getdNum() {
		return dNum;
	}
	public void setdNum(String dNum) {
		this.dNum = dNum;
	}
	public String getOiCount() {
		return oiCount;
	}
	public void setOiCount(String oiCount) {
		this.oiCount = oiCount;
	}
	public OrderItem(Integer id, String oiNum, String oNum, String dNum,
			String oiCount) {
		this.id = id;
		this.oiNum = oiNum;
		this.oNum = oNum;
		this.dNum = dNum;
		this.oiCount = oiCount;
	}
	public OrderItem() {
	}
	
	
}
