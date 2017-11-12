package com.choice.entity;

public class Dish {
	private Integer id;
	private String dNum;
	private String dcNum;
	private String dName;
	private String dCn;
	private String dDate;
	private String dMaterial;
	private String dRemark;
	private String dCount;
	private String dStatus;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getdNum() {
		return dNum;
	}
	public void setdNum(String dNum) {
		this.dNum = dNum;
	}
	public String getDcNum() {
		return dcNum;
	}
	public void setDcNum(String dcNum) {
		this.dcNum = dcNum;
	}
	public String getdName() {
		return dName;
	}
	public void setdName(String dName) {
		this.dName = dName;
	}
	public String getdCn() {
		return dCn;
	}
	public void setdCn(String dCn) {
		this.dCn = dCn;
	}
	public String getdDate() {
		return dDate;
	}
	public void setdDate(String dDate) {
		this.dDate = dDate;
	}
	public String getdMaterial() {
		return dMaterial;
	}
	public void setdMaterial(String dMaterial) {
		this.dMaterial = dMaterial;
	}
	public String getdRemark() {
		return dRemark;
	}
	public void setdRemark(String dRemark) {
		this.dRemark = dRemark;
	}
	public String getdCount() {
		return dCount;
	}
	public void setdCount(String dCount) {
		this.dCount = dCount;
	}
	public String getdStatus() {
		return dStatus;
	}
	public void setdStatus(String dStatus) {
		this.dStatus = dStatus;
	}
	public Dish(Integer id, String dNum, String dcNum, String dName,
			String dCn, String dDate, String dMaterial, String dRemark,
			String dCount, String dStatus) {
		this.id = id;
		this.dNum = dNum;
		this.dcNum = dcNum;
		this.dName = dName;
		this.dCn = dCn;
		this.dDate = dDate;
		this.dMaterial = dMaterial;
		this.dRemark = dRemark;
		this.dCount = dCount;
		this.dStatus = dStatus;
	}
		public Dish() {
		
		}
	
}
