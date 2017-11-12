package com.choice.entity;

public class OrderItem {
	
	private Integer id;
	private String oiNum;
	private String oNum;
	private String dNum;
	private String oiCount;
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
