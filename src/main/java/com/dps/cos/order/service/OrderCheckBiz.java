package com.dps.cos.order.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.dps.common.vo.DpsBizException;

@Service
public class OrderCheckBiz {
	private static final Logger logger = LoggerFactory.getLogger(OrderCheckBiz.class);
	
	public boolean chech4Order(long orderId)throws  DpsBizException, Exception
	{
		logger.info("");
		return false;
	}
	
	public boolean chech4Cancel(long orderId)throws  DpsBizException, Exception
	{
		//有没有支付过后不可退订的订单
		return true;
	}
}
