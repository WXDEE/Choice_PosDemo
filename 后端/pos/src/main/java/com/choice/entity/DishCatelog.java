package com.choice.entity;

public class DishCatelog {
	 private Integer id;
	 private String dcNum;
	 private String dcName;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDcNum() {
		return dcNum;
	}
	public void setDcNum(String dcNum) {
		this.dcNum = dcNum;
	}
	public String getDcName() {
		return dcName;
	}
	public void setDcName(String dcName) {
		this.dcName = dcName;
	}
	public DishCatelog(Integer id, String dcNum, String dcName) {
	
		this.id = id;
		this.dcNum = dcNum;
		this.dcName = dcName;
	}
	public DishCatelog() {
		
	}
 
 
}
