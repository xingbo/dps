package com.dps.cos.order.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dps.common.vo.PagedResult;
import com.dps.cos.order.dao.OrderItemDAO;
import com.dps.cos.order.vo.OrderItem;

@Service
public class OrderItemBiz {

	private static final  Logger logger = LoggerFactory.getLogger(OrderItemBiz.class);

	@Autowired
	private OrderItemDAO orderItemDAO;

	public PagedResult<OrderItem> listPagedOrderItems(int offset, int limit) throws Exception {
		PagedResult<OrderItem> result = new PagedResult<OrderItem>();
		int total = orderItemDAO.getOrderItemTotal();
		List<OrderItem> prs = orderItemDAO.getPagedOrderItems(offset, limit);
		result.setResults(prs);
		result.setTotal(total);
		return result;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void addOrderItem(OrderItem orderItem) throws Exception {
		// TODO Auto-generated method stub
		orderItemDAO.saveOrderItem(orderItem);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void modifyOrderItem(OrderItem orderItem) throws Exception {
		// TODO Auto-generated method stub
		orderItemDAO.updateOrderItem(orderItem);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void removeOrderItem(long id) throws Exception {
		// TODO Auto-generated method stub
		orderItemDAO.deleteOrderItem(id);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public OrderItem findOrderItem(long id) throws Exception {
		// TODO Auto-generated method stub
		OrderItem orderItem = orderItemDAO.getOrderItem(id);
		return orderItem;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void exampleMethod() throws Exception {
		// TODO Auto-generated method stub
		try{
			// TODO implements biz
		} catch(Exception e) {
			// TODO Auto-generated method stub
			logger.error("errer message", e);
		}
	}

}