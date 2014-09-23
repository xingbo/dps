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
import com.dps.cos.order.service.OrderHisBiz;
import com.dps.cos.order.vo.OrderHis;

@Controller
public class OrderHisController extends BaseController{

	private static final  Logger logger = LoggerFactory.getLogger(OrderHisController.class);

	@Autowired
	private OrderHisBiz orderHisBiz;

	@RequestMapping(value = "/orderHis/get", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> findOrderHis(@RequestParam("id") long id) {
		try{
			// TODO Auto-generated method stub
			OrderHis orderHis = orderHisBiz.findOrderHis(id);
			return data(orderHis);
		} catch(Exception e) {
			logger.error("errer message", e);
			return data(null);
		}
	}

	@RequestMapping(value = "/orderHis/paged/list", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> listPagedOrderHiss(@RequestParam("start") int start, @RequestParam("limit") int limit) {
		try{
			// TODO Auto-generated method stub
			PagedResult<OrderHis> results = orderHisBiz.listPagedOrderHiss(start, limit);
			return pagedResult(results.getResults(), results.getTotal());
		} catch(Exception e) {
			logger.error("errer message", e);
			return pagedResult(new ArrayList<OrderHis>(), 0);
		}
	}

	@RequestMapping(value = "/orderHis/save", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, Object> saveOrderHis(OrderHis orderHis) {
		try{
			// TODO Auto-generated method stub
			orderHisBiz.addOrderHis(orderHis);
			return success();
		} catch(Exception e) {
			logger.error("errer message", e);
			return fail();
		}
	}

	@RequestMapping(value = "/orderHis/update", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, Object> updateOrderHis(OrderHis orderHis) {
		try{
			// TODO Auto-generated method stub
			orderHisBiz.modifyOrderHis(orderHis);
			return success();
		} catch(Exception e) {
			logger.error("errer message", e);
			return fail();
		}
	}

	@RequestMapping(value = "/orderHis/del", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> delOrderHis(@RequestParam("id") long id) {
		try{
			// TODO Auto-generated method stub
			orderHisBiz.removeOrderHis(id);
			return success();
		} catch(Exception e) {
			logger.error("errer message", e);
			return fail();
		}
	}

}