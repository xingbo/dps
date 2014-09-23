package com.dps.cos.pay.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dps.common.vo.DpsBizException;
import com.dps.cos.order.service.OrderPayBiz;
import com.dps.cos.pay.dao.PayChannelDAO;
import com.dps.cos.pay.dao.PayInfoDAO;
import com.dps.cos.pay.vo.PayChannel;
import com.dps.cos.pay.vo.PayInfo;

@Service
public class PayServiceBiz {
	private static final Logger logger = LoggerFactory.getLogger(PayServiceBiz.class);

	@Autowired
	private PayInfoDAO payInfoDAO;

	@Autowired
	private PayChannelDAO payChannelDAO;

	private OrderPayBiz orderPayBiz;

	@Transactional(propagation = Propagation.REQUIRED)
	public boolean pay(long payId, long channelId) throws DpsBizException, Exception {
		PayInfo payInfo = payInfoDAO.getPayInfo(payId);

		if (payInfo == null) {
			logger.info("");
			throw new DpsBizException("", "");
		}

		payInfo.setChannelId(channelId);
		return pay(payInfo);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public boolean pay(PayInfo payInfo) throws DpsBizException, Exception {
		if (!checkStatus4Pay(payInfo)) {
			logger.info("");
			throw new DpsBizException("", "");
		}

		PayChannel pc = checkPayChannel(payInfo.getChannelId());
		payInfoDAO.updatePayInfoStatus(payInfo.getId(), PayInfo.STATUS_PAYING);
		pc.getType();
		// 支付

		return true;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public boolean refund(long payid) throws DpsBizException, Exception {
		PayInfo payInfo = payInfoDAO.getPayInfo(payid);

		if (payInfo == null) {
			logger.info("");
			throw new DpsBizException("", "");
		}

		return refund(payInfo);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public boolean refund(PayInfo payInfo) throws DpsBizException, Exception {
		if (!checkStatus4Refund(payInfo)) {
			logger.info("");
			throw new DpsBizException("", "");
		}

		PayChannel pc = checkPayChannel(payInfo.getChannelId());
		payInfoDAO.updatePayInfoStatus(payInfo.getId(), PayInfo.STATUS_REFUNDING);

		pc.getType();
		// 支付退款

		return true;
	}

	public PayChannel checkPayChannel(long channelId) throws DpsBizException, Exception {
		PayChannel pc = payChannelDAO.getPayChannel(channelId);

		if (pc == null) {
			logger.info("");
			throw new DpsBizException("", "");
		}

		if (pc.getStatus() != PayChannel.STATUS_ENABLE) {
			logger.info("");
			throw new DpsBizException("", "");
		}

		return pc;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public boolean payCallback(long payId, int result, String desc) throws DpsBizException, Exception {
		// 支付
		PayInfo pi = payInfoDAO.getPayInfo(payId);
		if (pi != null) {
			payInfoDAO.updatePayInfoStatusAndResult(payId, PayInfo.STATUS_PAIED, PayInfo.RESULT_SUCC);
			this.orderPayBiz.orderPaySucc(pi.getOrderId());
		}
		return false;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public boolean refundCallback(long payId, int result, String desc) throws DpsBizException, Exception {

		// 支付退款
		PayInfo pi = payInfoDAO.getPayInfo(payId);
		if (pi != null) {
			payInfoDAO.updatePayInfoStatusAndResult(payId, PayInfo.STATUS_REFUNDED, PayInfo.RESULT_SUCC);
			this.orderPayBiz.orderRefundSucc(pi.getOrderId());
		}

		return false;
	}

	protected boolean checkStatus4Pay(PayInfo payInfo) {
		return (payInfo.getStatus() == PayInfo.STATUS_PAIED && payInfo.getResult() == PayInfo.RESULT_FAIL) || (payInfo.getStatus() == PayInfo.STATUS_UNPAY);
	}

	protected boolean checkStatus4Refund(PayInfo payInfo) {
		return (payInfo.getStatus() == PayInfo.STATUS_PAIED && payInfo.getResult() == PayInfo.RESULT_SUCC) || (payInfo.getStatus() == PayInfo.STATUS_PAYING);
	}

	public void setOrderPayBiz(OrderPayBiz orderPayBiz) {
		this.orderPayBiz = orderPayBiz;
	}
}
