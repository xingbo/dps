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
import com.dps.cos.order.service.OrderManageBiz;
import com.dps.cos.order.service.ServiceOrderBiz;
import com.dps.cos.order.vo.Order;
import com.dps.cos.order.vo.OrderRequest;

@Controller
public class OrderController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

	@Autowired
	private OrderManageBiz orderBiz;

	@Autowired
	private ServiceOrderBiz serviceOrderBiz;

	@RequestMapping(value = "/order/get", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> findOrder(@RequestParam("id") long id) {
		try {
			// TODO Auto-generated method stub
			Order order = orderBiz.findOrder(id);
			return data(order);
		} catch (Exception e) {
			logger.error("errer message", e);
			return data(null);
		}
	}

	@RequestMapping(value = "/order/paged/list", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> listPagedOrders(@RequestParam("start") int start, @RequestParam("limit") int limit) {
		try {
			// TODO Auto-generated method stub
			PagedResult<Order> results = orderBiz.listPagedOrders(start, limit);
			return pagedResult(results.getResults(), results.getTotal());
		} catch (Exception e) {
			logger.error("errer message", e);
			return pagedResult(new ArrayList<Order>(), 0);
		}
	}

	@RequestMapping(value = "/order/cart", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, Object> orderService(OrderRequest request) {
		try {
			serviceOrderBiz.orderService(request);
			return success();
		} catch (Exception e) {
			logger.error("errer message", e);
			return fail(e.getMessage());
		}
	}

	@RequestMapping(value = "/order/update", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, Object> updateOrder(Order order) {
		try {
			// TODO Auto-generated method stub
			orderBiz.modifyOrder(order);
			return success();
		} catch (Exception e) {
			logger.error("errer message", e);
			return fail();
		}
	}

	@RequestMapping(value = "/order/cancel", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> cancelOrder(@RequestParam("id") long id) {
		try {
			// TODO Auto-generated method stub
			// orderBiz.removeOrder(id);
			return success();
		} catch (Exception e) {
			logger.error("errer message", e);
			return fail();
		}
	}

}