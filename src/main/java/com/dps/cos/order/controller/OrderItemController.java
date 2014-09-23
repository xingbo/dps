package com.dps.cos.order.controller;

import java.util.ArrayList;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dps.common.vo.PagedResult;
import com.dps.common.web.BaseController;
import com.dps.cos.order.service.OrderItemBiz;
import com.dps.cos.order.vo.OrderItem;

@Controller
public class OrderItemController extends BaseController{

	private static final  Logger logger = LoggerFactory.getLogger(OrderItemController.class);

	@Autowired
	private OrderItemBiz orderItemBiz;

	@RequestMapping(value = "/orderItem/get", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> findOrderItem(@RequestParam("id") long id) {
		try{
			// TODO Auto-generated method stub
			OrderItem orderItem = orderItemBiz.findOrderItem(id);
			return data(orderItem);
		} catch(Exception e) {
			logger.error("errer message", e);
			return data(null);
		}
	}

	@RequestMapping(value = "/orderItem/paged/list", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> listPagedOrderItems(@RequestParam("start") int start, @RequestParam("limit") int limit) {
		try{
			// TODO Auto-generated method stub
			PagedResult<OrderItem> results = orderItemBiz.listPagedOrderItems(start, limit);
			return pagedResult(results.getResults(), results.getTotal());
		} catch(Exception e) {
			logger.error("errer message", e);
			return pagedResult(new ArrayList<OrderItem>(), 0);
		}
	}

	@RequestMapping(value = "/orderItem/save", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, Object> saveOrderItem(OrderItem orderItem) {
		try{
			// TODO Auto-generated method stub
			orderItemBiz.addOrderItem(orderItem);
			return success();
		} catch(Exception e) {
			logger.error("errer message", e);
			return fail();
		}
	}

	@RequestMapping(value = "/orderItem/update", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, Object> updateOrderItem(OrderItem orderItem) {
		try{
			// TODO Auto-generated method stub
			orderItemBiz.modifyOrderItem(orderItem);
			return success();
		} catch(Exception e) {
			logger.error("errer message", e);
			return fail();
		}
	}

	@RequestMapping(value = "/orderItem/del", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> delOrderItem(@RequestParam("id") long id) {
		try{
			// TODO Auto-generated method stub
			orderItemBiz.removeOrderItem(id);
			return success();
		} catch(Exception e) {
			logger.error("errer message", e);
			return fail();
		}
	}

}