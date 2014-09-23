package com.dps.cos.pay.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dps.common.vo.PagedResult;
import com.dps.cos.pay.dao.PayInfoDAO;
import com.dps.cos.pay.vo.PayInfo;

@Service
public class PayInfoBiz {

	private static final Logger logger = LoggerFactory.getLogger(PayInfoBiz.class);

	@Autowired
	private PayInfoDAO payInfoDAO;

	@Autowired
	private PayServiceBiz payServiceBiz;

	public PagedResult<PayInfo> listPagedPayInfos(int offset, int limit) throws Exception {
		PagedResult<PayInfo> result = new PagedResult<PayInfo>();
		int total = payInfoDAO.getPayInfoTotal();
		List<PayInfo> prs = payInfoDAO.getPagedPayInfos(offset, limit);
		result.setResults(prs);
		result.setTotal(total);
		return result;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void addPayInfoAndPay(PayInfo payInfo, long channelId) throws Exception {
		payInfo.setResult(PayInfo.RESULT_FAIL);
		payInfo.setStatus(PayInfo.STATUS_UNPAY);
		payInfo.setChannelId(channelId);
		payInfo.setCreateDate(new Date());
		payInfoDAO.savePayInfo(payInfo);
		payServiceBiz.pay(payInfo);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void modifyPayInfo(PayInfo payInfo) throws Exception {
		// TODO Auto-generated method stub
		payInfoDAO.updatePayInfo(payInfo);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void removePayInfo(long id) throws Exception {
		// TODO Auto-generated method stub
		payInfoDAO.deletePayInfo(id);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public PayInfo findPayInfo(long id) throws Exception {
		// TODO Auto-generated method stub
		PayInfo payInfo = payInfoDAO.getPayInfo(id);
		return payInfo;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void exampleMethod() throws Exception {
		// TODO Auto-generated method stub
		try {
			// TODO implements biz
		} catch (Exception e) {
			// TODO Auto-generated method stub
			logger.error("errer message", e);
		}
	}

}