package com.dps.cos.order.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dps.common.vo.DpsBizException;
import com.dps.cos.order.dao.OrderDAO;
import com.dps.cos.order.dao.OrderItemDAO;
import com.dps.cos.order.dao.ServiceOrderedAmountDAO;
import com.dps.cos.order.vo.Order;
import com.dps.cos.order.vo.OrderItem;

@Service
public class OrderCancellBiz {
	private static final Logger logger = LoggerFactory.getLogger(OrderCancellBiz.class);

	@Autowired
	private OrderDAO orderDAO;

	@Autowired
	private OrderCheckBiz orderCheckBiz;

	@Autowired
	private OrderPayBiz orderPayBiz;

	@Autowired
	private OrderItemDAO orderItemDAO;

	@Autowired
	private ServiceOrderedAmountDAO serviceOrderedAmountDAO;

	@Transactional(propagation = Propagation.REQUIRED)
	public boolean cancel(long orderId) throws DpsBizException, Exception {
		Order order = orderDAO.getOrder(orderId);
		if (order == null) {
			logger.info("");
			throw new DpsBizException("", "无此订单");
		}

		boolean ok = checkOrderStatus(order);

		if (!ok) {
			logger.info("");
			throw new DpsBizException("", "此单在不可退订状态");
		}

		List<OrderItem> items = orderItemDAO.findOrderItemByOrder(orderId);
		order.setOrderItems(items);
		ok = checkOrderService(order);

		if (!ok) {
			logger.info("");
			throw new DpsBizException("", "此单在不可退订");
		}

		orderPayBiz.cancelPay(orderId);

		for (OrderItem item : items) {
			serviceOrderedAmountDAO.updateServiceOrderedAmountdecr(item.getServiceId(), item.getServiceTime(), item.getAmount());
		}

		orderDAO.updateOrderStatus(order.getId(), Order.STATUS_CANCEL);
		return true;
	}

	public boolean checkOrderService(Order order) {
		// 有没有支付过后不可退订的订单 在 某时间段内不可退
		return true;
	}

	protected boolean checkOrderStatus(Order order) {
		return order.getStatus() == Order.STATUS_BOOK || order.getStatus() == Order.STATUS_PAYING || order.getStatus() == Order.STATUS_PAYING;
	}

}
