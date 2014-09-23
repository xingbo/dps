package com.dps.cos.pay.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dps.common.vo.PagedResult;
import com.dps.cos.pay.dao.PayChannelDAO;
import com.dps.cos.pay.vo.PayChannel;

@Service
public class PayChannelBiz {

	private static final  Logger logger = LoggerFactory.getLogger(PayChannelBiz.class);

	@Autowired
	private PayChannelDAO payChannelDAO;

	public PagedResult<PayChannel> listPagedPayChannels(int offset, int limit) throws Exception {
		PagedResult<PayChannel> result = new PagedResult<PayChannel>();
		int total = payChannelDAO.getPayChannelTotal();
		List<PayChannel> prs = payChannelDAO.getPagedPayChannels(offset, limit);
		result.setResults(prs);
		result.setTotal(total);
		return result;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void addPayChannel(PayChannel payChannel) throws Exception {
		// TODO Auto-generated method stub
		payChannelDAO.savePayChannel(payChannel);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void modifyPayChannel(PayChannel payChannel) throws Exception {
		// TODO Auto-generated method stub
		payChannelDAO.updatePayChannel(payChannel);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void removePayChannel(long id) throws Exception {
		// TODO Auto-generated method stub
		payChannelDAO.deletePayChannel(id);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public PayChannel findPayChannel(long id) throws Exception {
		// TODO Auto-generated method stub
		PayChannel payChannel = payChannelDAO.getPayChannel(id);
		return payChannel;
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