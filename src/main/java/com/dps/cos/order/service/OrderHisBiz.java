package com.dps.cos.order.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dps.common.vo.PagedResult;
import com.dps.cos.order.dao.OrderHisDAO;
import com.dps.cos.order.vo.OrderHis;

@Service
public class OrderHisBiz {

	private static final  Logger logger = LoggerFactory.getLogger(OrderHisBiz.class);

	@Autowired
	private OrderHisDAO orderHisDAO;

	public PagedResult<OrderHis> listPagedOrderHiss(int offset, int limit) throws Exception {
		PagedResult<OrderHis> result = new PagedResult<OrderHis>();
		int total = orderHisDAO.getOrderHisTotal();
		List<OrderHis> prs = orderHisDAO.getPagedOrderHiss(offset, limit);
		result.setResults(prs);
		result.setTotal(total);
		return result;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void addOrderHis(OrderHis orderHis) throws Exception {
		// TODO Auto-generated method stub
		orderHisDAO.saveOrderHis(orderHis);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void modifyOrderHis(OrderHis orderHis) throws Exception {
		// TODO Auto-generated method stub
		orderHisDAO.updateOrderHis(orderHis);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void removeOrderHis(long id) throws Exception {
		// TODO Auto-generated method stub
		orderHisDAO.deleteOrderHis(id);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public OrderHis findOrderHis(long id) throws Exception {
		// TODO Auto-generated method stub
		OrderHis orderHis = orderHisDAO.getOrderHis(id);
		return orderHis;
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