package com.dps.order.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Cart {
	private long userId;
	private Date orderTime;
	private List<CartItem> items = new ArrayList<CartItem>();

	public static class CartItem {
		private long productItemId;
		private int amount;
		private Date serviceTime;

		public long getProductItemId() {
			return productItemId;
		}

		public void setProductItemId(long productItemId) {
			this.productItemId = productItemId;
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

	public List<CartItem> getItems() {
		return items;
	}

	public void setItems(List<CartItem> items) {
		this.items = items;
	}

	public void addItem(CartItem item) {
		this.items.add(item);
	}
}
