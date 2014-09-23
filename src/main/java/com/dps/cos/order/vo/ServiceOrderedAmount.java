package com.dps.cos.order.vo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ServiceOrderedAmount {
	private long id;
	private long serviceId;
	private Date serviceDate;
	private int amount = 0;
	private DateFormat df;

	public ServiceOrderedAmount() {
		df = new SimpleDateFormat("yyyyMMddHHmmss");
	}

	public ServiceOrderedAmount(long serviceId, Date serviceDate) {
		this();
		this.serviceDate = serviceDate;
		this.serviceId = serviceId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getServiceId() {
		return serviceId;
	}

	public void setServiceId(long serviceId) {
		this.serviceId = serviceId;
	}

	public Date getServiceDate() {
		return serviceDate;
	}

	public void setServiceDate(Date serviceDate) {
		this.serviceDate = serviceDate;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int mount) {
		this.amount = mount;
	}

	public String uniServiceKey() {
		return this.serviceId + "@" + this.df.format(this.serviceDate);
	}
}
