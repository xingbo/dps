package com.dps.cos.order.vo;

import java.util.Date;

public class OrderItem {
	public static final int STATUS_CANCEL = 2;
	public static final int STATUS_ORDER = 1;
	public static final int STATUS_USED = 3;
	
	private long id;

	private long serviceId;
	
	private String title;

	private int amount;
	
	private int price;

	private long orderId;

	private int status;

	private Date createDate;
	
	private Date serviceTime;

	private Date updateDate;


	public long getId(){
		return this.id;
	}

	public void setId(long id){
		this.id = id;
	}

	public long getServiceId(){
		return this.serviceId;
	}

	public void setServiceId(long serviceId){
		this.serviceId = serviceId;
	}

	public int getAmount(){
		return this.amount;
	}

	public void setAmount(int amount){
		this.amount = amount;
	}

	public long getOrderId(){
		return this.orderId;
	}

	public void setOrderId(long orderId){
		this.orderId = orderId;
	}

	public int getStatus(){
		return this.status;
	}

	public void setStatus(int status){
		this.status = status;
	}

	public Date getCreateDate(){
		return this.createDate;
	}

	public void setCreateDate(Date createDate){
		this.createDate = createDate;
	}

	public Date getUpdateDate(){
		return this.updateDate;
	}

	public void setUpdateDate(Date updateDate){
		this.updateDate = updateDate;
	}

	public Date getServiceTime() {
		return serviceTime;
	}

	public void setServiceTime(Date serviceDate) {
		this.serviceTime = serviceDate;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
