package com.dps.cos.order.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order {
	public static final int STATUS_BOOK = 1;
	public static final int STATUS_PAYING = 2;
	public static final int STATUS_PAYED = 3;
	public static final int STATUS_USING = 4;
	public static final int STATUS_CANCEL = 5;
	public static final int STATUS_USED = 6;

	private long id;

	private String title;
	
	private String code;

	private int type;

	private long userId;

	private long serviceId;

	private int status;

	private String description;

	private Date createDate;

	private Date updateDate;
	
	private int fee;
	
	private List<OrderItem> orderItems = new ArrayList<OrderItem>();

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getType() {
		return this.type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public long getUserId() {
		return this.userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getServiceId() {
		return this.serviceId;
	}

	public void setServiceId(long serviceId) {
		this.serviceId = serviceId;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	public void addOrderItem(OrderItem orderItem) {
		this.orderItems.add(orderItem);
	}

	public int getFee() {
		return fee;
	}

	public void setFee(int fee) {
		this.fee = fee;
	}

	public String getPhone() {
		// TODO Auto-generated method stub
		return null;
	}

}
