package com.dps.cos.order.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderRequest {
	private long userId;
	private Date orderTime;
	private List<OrderServiceItem> items = new ArrayList<OrderServiceItem>();

	public static class OrderServiceItem {
		private long serviceItemId;
		private int amount;
		private Date serviceTime;

		public long getServiceItemId() {
			return serviceItemId;
		}

		public void setServiceItemId(long productItemId) {
			this.serviceItemId = productItemId;
		}

		public int getAmount() {
			return amount;
		}

		public void setAmount(int amount) {
			this.amount = amount;
		}

		public Date getServiceTime() {
			return serviceTime;
		}

		public void setServiceTime(Date serviceTime) {
			this.serviceTime = serviceTime;
		}
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public Date getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

	public List<OrderServiceItem> getItems() {
		return items;
	}

	public void setItems(List<OrderServiceItem> items) {
		this.items = items;
	}

	public void addItem(OrderServiceItem item) {
		this.items.add(item);
	}
}
