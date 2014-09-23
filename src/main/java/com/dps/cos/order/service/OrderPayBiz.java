package com.dps.cos.order.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dps.common.service.UniCodeGenerator;
import com.dps.common.vo.DpsBizException;
import com.dps.cos.order.dao.OrderDAO;
import com.dps.cos.order.dao.OrderItemDAO;
import com.dps.cos.order.dao.ServicePinCodeDAO;
import com.dps.cos.order.vo.Order;
import com.dps.cos.order.vo.OrderItem;
import com.dps.cos.order.vo.ServicePinCode;
import com.dps.cos.pay.dao.PayInfoDAO;
import com.dps.cos.pay.service.PayInfoBiz;
import com.dps.cos.pay.service.PayServiceBiz;
import com.dps.cos.pay.vo.PayInfo;

@Service
public class OrderPayBiz {

	private static final Logger logger = LoggerFactory.getLogger(OrderPayBiz.class);

	@Autowired
	private PayInfoBiz payInfoBiz;

	@Autowired
	private PayInfoDAO payInfoDAO;

	@Autowired
	private OrderDAO orderDAO;

	@Autowired
	private OrderItemDAO orderItemDAO;

	@Autowired
	private UniCodeGenerator uniCodeGenerator;

	@Autowired
	private ServicePinCodeDAO servicePinCodeDAO;

	private PayServiceBiz payServiceBiz;

	@Autowired
	public void setPayServiceBiz(PayServiceBiz payServiceBiz) {
		this.payServiceBiz = payServiceBiz;
		this.payServiceBiz.setOrderPayBiz(this);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public boolean payOrder(long orderId, long payChannelId) throws DpsBizException, Exception {
		Order order = orderDAO.getOrder(orderId);

		if (order == null) {
			logger.info("");
			throw new DpsBizException("", "");
		}

		if (order.getStatus() != Order.STATUS_BOOK) {
			logger.info("");
			throw new DpsBizException("", "");
		}

		PayInfo pay = new PayInfo();
		pay.setOrderId(order.getId());
		pay.setTitle(order.getTitle());
		pay.setUserId(order.getUserId());
		pay.setFee(order.getFee());
		payInfoBiz.addPayInfoAndPay(pay, payChannelId);

		return true;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public boolean orderPaySucc(long orderId) throws DpsBizException, Exception {
		Order order = orderDAO.getOrder(orderId);

		if (order == null) {
			logger.info("");
			throw new DpsBizException("", "");
		}

		orderDAO.updateOrderStatus(orderId, Order.STATUS_PAYED);
		List<OrderItem> items = orderItemDAO.findOrderItemByOrder(orderId);

		for (OrderItem item : items) {
			if (item.getStatus() == OrderItem.STATUS_ORDER) {
				String code = uniCodeGenerator.geneteUniCode(UniCodeGenerator.TYPE_SERVICE_USED);
				ServicePinCode spc = new ServicePinCode();
				spc.setCode(code);
				spc.setCreateTime(new Date());
				spc.setExpiredTime(new Date());
				spc.setOrderId(order.getId());
				spc.setPhone(order.getPhone());
				spc.setUserId(order.getUserId());
				spc.setStatus(ServicePinCode.STATUS_VALIDATED);
				spc.setServiceTime(item.getServiceTime());
				spc.setOrderItemId(item.getId());
				servicePinCodeDAO.saveServicePinCode(spc);
			}
		}

		return true;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public boolean cancelPay(long orderId) throws DpsBizException, Exception {
		List<PayInfo> payInfos = payInfoDAO.findAvailablePayinfoByOrder(orderId);

		if (payInfos == null) {
			logger.info("");
			throw new DpsBizException("", "");
		}

		for (PayInfo pay : payInfos) {
			if (pay.getStatus() == PayInfo.STATUS_PAIED || pay.getStatus() == PayInfo.STATUS_PAYING) {
				payServiceBiz.refund(pay.getId());
			} else {

			}
		}

		return true;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public boolean orderRefundSucc(long orderId) throws DpsBizException, Exception {
		Order order = orderDAO.getOrder(orderId);

		if (order == null) {
			logger.info("");
			throw new DpsBizException("", "");
		}

		orderDAO.updateOrderStatus(orderId, Order.STATUS_PAYED);
		servicePinCodeDAO.updateStatusByOrderId(order.getId(), ServicePinCode.STATUS_CANCELATED);
		//

		return true;
	}
}
