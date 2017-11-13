package com.choice.entity;

public class Dish {
	private Integer id;
	private String dNum;//菜品编号
	private String dcNum;//菜品类目编号
	private String dName;//菜品名称
	private String dCn;//菜品汉拼首字母
	private String dDate;//菜品上架日期
	private String dMaterial;//菜品原料
	private String dRemark;//菜品备注
	private String dCount;//菜品库存数量
	private String dPrice;//菜品价格
	private String dStatus;//菜品状态 0：已删除 1：未删除
	public String getdPrice() {
		return dPrice;
	}

	public void setdPrice(String dPrice) {
		this.dPrice = dPrice;
	}
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
