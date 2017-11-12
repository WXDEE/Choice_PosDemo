package com.choice.entity;

public class Desk {
	private Integer id;
	private String deNum;
	private String deStatus;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDeNum() {
		return deNum;
	}
	public void setDeNum(String deNum) {
		this.deNum = deNum;
	}
	public String getDeStatus() {
		return deStatus;
	}
	public void setDeStatus(String deStatus) {
		this.deStatus = deStatus;
	}
	public Desk(Integer id, String deNum, String deStatus) {
		this.id = id;
		this.deNum = deNum;
		this.deStatus = deStatus;
	}
	public Desk() {
	}

}
