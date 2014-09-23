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
import com.dps.cos.order.dao.ServiceOrderedAmountDAO;
import com.dps.cos.order.dao.ServicePinCodeDAO;
import com.dps.cos.order.vo.Order;
import com.dps.cos.order.vo.OrderItem;
import com.dps.cos.order.vo.OrderRequest;
import com.dps.cos.order.vo.OrderResult;
import com.dps.cos.order.vo.ServiceOrderedAmount;
import com.dps.cos.order.vo.ServicePinCode;
import com.dps.css.service.service.ServiceCheckBiz;
import com.dps.css.service.vo.DPService;
import com.dps.user.service.UserBiz;
import com.dps.user.domain.User;

@Service
public class ServiceOrderBiz {

	private static final Logger logger = LoggerFactory.getLogger(ServiceOrderBiz.class);

	@Autowired
	private OrderDAO orderDAO;

	@Autowired
	private OrderItemDAO orderItemDAO;

	@Autowired
	private UserBiz userBiz;

	@Autowired
	private ServiceCheckBiz serviceCheckBiz;

	@Autowired
	private UniCodeGenerator uniCodeGenerator;

	@Autowired
	private ServiceOrderedAmountDAO serviceOrderedAmountDAO;

	@Autowired
	private ServicePinCodeDAO servicePinCodeDAO;

	@Transactional(propagation = Propagation.REQUIRED)
	public OrderResult orderService(OrderRequest request) throws DpsBizException, Exception {
		User user = userBiz.checkUser4Order(request.getUserId());

		if (user == null) {
			logger.info("");
			throw new DpsBizException("", "用户不合法");
		}

		Order o = new Order();
		int fee = 0;
		ServiceOrderedAmount soa;
		for (OrderRequest.OrderServiceItem item : request.getItems()) {
			DPService service = serviceCheckBiz.checkService4Order(item.getServiceItemId());
			if (service == null) {
				throw new DpsBizException("", "服务不可用");
			}

			int amount = service.getTotalAmount4ServiceDate(item.getServiceTime());
			soa = serviceOrderedAmountDAO.getServiceOrderedAmountByServiceIdAndServiceDate(service.getId(), item.getServiceTime());

			if (soa == null) {
				soa = new ServiceOrderedAmount(item.getServiceItemId(), item.getServiceTime());
			}

			amount = amount - soa.getAmount() - item.getAmount();

			if (amount < 0) {
				logger.info("");
				throw new DpsBizException("", "数量不足");
			} else {
				soa.setAmount(item.getAmount() + soa.getAmount());
			}

			if (soa.getId() > 0) {
				serviceOrderedAmountDAO.updateServiceOrderedAmountInr(soa.getId(), soa.getAmount());
			} else {
				serviceOrderedAmountDAO.saveServiceOrderedAmount(soa);
			}

			OrderItem oi = new OrderItem();
			oi.setAmount(item.getAmount());
			oi.setCreateDate(new Date());
			oi.setServiceTime(item.getServiceTime());
			oi.setServiceId(service.getId());
			oi.setTitle(service.getTitle());
			oi.setPrice(service.getPrice4ServiceDate(item.getServiceTime()));

			fee += oi.getAmount() * oi.getPrice();
			o.addOrderItem(oi);
		}

		String code = uniCodeGenerator.geneteUniCode(UniCodeGenerator.TYPE_ORDER);
		o.setCode(code);
		o.setCreateDate(new Date());
		o.setStatus(Order.STATUS_BOOK);
		o.setUserId(user.getId());
		o.setFee(fee);
		orderDAO.saveOrder(o);

		for (OrderItem oi : o.getOrderItems()) {
			oi.setOrderId(o.getId());
			orderItemDAO.saveOrderItem(oi);
		}

		OrderResult or = new OrderResult();
		return or;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void consumeService(long orderId, String[] pinCodes) throws DpsBizException, Exception {
		Order order = orderDAO.getOrder(orderId);

		if (order == null) {
			logger.info("");
			throw new DpsBizException("", "");
		}

		if (order.getStatus() != Order.STATUS_PAYED || order.getStatus() != Order.STATUS_USING) {
			logger.info("");
			throw new DpsBizException("", "");
		}

		Date now = new Date();

		List<ServicePinCode> spcs = servicePinCodeDAO.findServicePinCodesByOrderIdAndCodes(orderId, pinCodes);
		OrderItem item;
		for (ServicePinCode spc : spcs) {
			if (now.after(spc.getServiceTime())) {
				throw new DpsBizException("", "");
			}

			if (spc.getStatus() != ServicePinCode.STATUS_VALIDATED) {
				throw new DpsBizException("", "");
			}

			item = orderItemDAO.getOrderItem(spc.getOrderItemId());

			if (item.getStatus() != OrderItem.STATUS_ORDER) {
				throw new DpsBizException("", "");
			}

			orderItemDAO.updateOrderItemStatus(item.getId(), OrderItem.STATUS_USED);
			servicePinCodeDAO.updateStatusById(spc.getId(), ServicePinCode.STATUS_USED);
		}

		int count = servicePinCodeDAO.getCountByOrderIdAndStatus(orderId, ServicePinCode.STATUS_VALIDATED);
		if (count > 0) {
			orderDAO.updateOrderStatus(orderId, Order.STATUS_USING);
		} else {
			orderDAO.updateOrderStatus(orderId, Order.STATUS_USED);
		}
	}
}
