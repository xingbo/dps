package com.dps.cos.order.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dps.common.vo.DpsBizException;
import com.dps.common.vo.PagedResult;
import com.dps.cos.order.dao.OrderDAO;
import com.dps.cos.order.vo.Order;

@Service
public class OrderManageBiz {

	private static final Logger logger = LoggerFactory.getLogger(OrderManageBiz.class);

	@Autowired
	private OrderDAO orderDAO;

	public PagedResult<Order> listPagedOrders(int offset, int limit) throws DpsBizException, Exception {
		PagedResult<Order> result = new PagedResult<Order>();
		int total = orderDAO.getOrderTotal();
		List<Order> prs = orderDAO.getPagedOrders(offset, limit);
		result.setResults(prs);
		result.setTotal(total);
		return result;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void modifyOrder(Order order) throws DpsBizException, Exception {
		// TODO Auto-generated method stub
		orderDAO.updateOrder(order);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void removeOrder(long id) throws DpsBizException, Exception {
		// TODO Auto-generated method stub
		orderDAO.deleteOrder(id);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public Order findOrder(long id) throws DpsBizException, Exception {
		// TODO Auto-generated method stub
		Order order = orderDAO.getOrder(id);
		return order;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void exampleMethod() throws DpsBizException, Exception {
		// TODO Auto-generated method stub
		try {
			// TODO implements biz
		} catch (Exception e) {
			// TODO Auto-generated method stub
			logger.error("errer message", e);
		}
	}

}