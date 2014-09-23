package com.dps.cos.pay.vo;

import java.util.Date;

public class PayInfo {
	public final static int STATUS_UNPAY = 1;
	public final static int STATUS_PAYING = 2;
	public final static int STATUS_PAIED = 3;
	public final static int STATUS_CANCEL = 4;
	public final static int STATUS_REFUNDING = 5;
	public final static int STATUS_REFUNDED = 6;
	public final static int RESULT_SUCC = 1;
	public final static int RESULT_FAIL = 2;

	private long id;

	private String title;

	private int result;

	private int type;

	private long userId;

	private long orderId;

	private int fee;

	private int status;

	private String description;

	private Date createDate;

	private Date updateDate;
	
	private long channelId;

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

	public long getOrderId() {
		return this.orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public int getFee() {
		return this.fee;
	}

	public void setFee(int fee) {
		this.fee = fee;
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

	public int getResult(){
		return this.result;
	}

	public void setResult(int result){
		this.result = result;
	}

	public long getChannelId() {
		return channelId;
	}

	public void setChannelId(long channelId) {
		this.channelId = channelId;
	}

}
