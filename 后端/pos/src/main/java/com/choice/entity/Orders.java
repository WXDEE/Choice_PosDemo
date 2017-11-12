package com.choice.entity;

public class Orders {
	private Integer id;
	private String oNum;
	private String oDate;
	private String oStatus;
	private String deNum;
	private String oTotal;
	private String odCount;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getoNum() {
		return oNum;
	}
	public void setoNum(String oNum) {
		this.oNum = oNum;
	}
	public String getoDate() {
		return oDate;
	}
	public void setoDate(String oDate) {
		this.oDate = oDate;
	}
	public String getoStatus() {
		return oStatus;
	}
	public void setoStatus(String oStatus) {
		this.oStatus = oStatus;
	}
	public String getDeNum() {
		return deNum;
	}
	public void setDeNum(String deNum) {
		this.deNum = deNum;
	}
	public String getoTotal() {
		return oTotal;
	}
	public void setoTotal(String oTotal) {
		this.oTotal = oTotal;
	}
	public String getOdCount() {
		return odCount;
	}
	public void setOdCount(String odCount) {
		this.odCount = odCount;
	}
	public Orders() {
	
	}
	public Orders(Integer id, String oNum, String oDate, String oStatus,
			String deNum, String oTotal, String odCount) {
		this.id = id;
		this.oNum = oNum;
		this.oDate = oDate;
		this.oStatus = oStatus;
		this.deNum = deNum;
		this.oTotal = oTotal;
		this.odCount = odCount;
	}
	
}
