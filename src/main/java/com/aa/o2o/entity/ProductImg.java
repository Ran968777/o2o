package com.aa.o2o.entity;

import java.util.Date;

public class ProductImg {
	private Long productImgId;
	private String imgAddr;
	private String imgDescr;
	private Integer priority;
	private Date createTime;
	private Long produnctId;
	public Long getProductImgId() {
		return productImgId;
	}
	public void setProductImgId(Long productImgId) {
		this.productImgId = productImgId;
	}
	public String getImgAddr() {
		return imgAddr;
	}
	public void setImgAddr(String imgAddr) {
		this.imgAddr = imgAddr;
	}
	public String getImgDescr() {
		return imgDescr;
	}
	public void setImgDescr(String imgDescr) {
		this.imgDescr = imgDescr;
	}
	public Integer getPriority() {
		return priority;
	}
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Long getProdunctId() {
		return produnctId;
	}
	public void setProdunctId(Long produnctId) {
		this.produnctId = produnctId;
	}
	 
}
