package com.atguigu.bookstore.bean;

import java.io.Serializable;

public class Order implements Serializable{
	private static final long serialVersionUID = 1L;
	private String id;
	private double totalAmount;
	private int totalCount;
	private int state;
	private String createTime;
	private int userId;
	
	public Order(String id, double totalAmount, int totalCount, int state, String createTime, int userId) {
		super();
		this.id = id;
		this.totalAmount = totalAmount;
		this.totalCount = totalCount;
		this.state = state;
		this.createTime = createTime;
		this.userId = userId;
	}
	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	@Override
	public String toString() {
		return "Order [id=" + id + ", totalAmount=" + totalAmount + ", totalCount=" + totalCount + ", state=" + state
				+ ", createTime=" + createTime + ", userId=" + userId + "]";
	}
}
