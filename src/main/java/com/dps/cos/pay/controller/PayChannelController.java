package com.dps.cos.pay.controller;

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
import com.dps.cos.pay.service.PayChannelBiz;
import com.dps.cos.pay.vo.PayChannel;

@Controller
public class PayChannelController extends BaseController{

	private static final  Logger logger = LoggerFactory.getLogger(PayChannelController.class);

	@Autowired
	private PayChannelBiz payChannelBiz;

	@RequestMapping(value = "/payChannel/get", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> findPayChannel(@RequestParam("id") long id) {
		try{
			// TODO Auto-generated method stub
			PayChannel payChannel = payChannelBiz.findPayChannel(id);
			return data(payChannel);
		} catch(Exception e) {
			logger.error("errer message", e);
			return data(null);
		}
	}

	@RequestMapping(value = "/payChannel/paged/list", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> listPagedPayChannels(@RequestParam("start") int start, @RequestParam("limit") int limit) {
		try{
			// TODO Auto-generated method stub
			PagedResult<PayChannel> results = payChannelBiz.listPagedPayChannels(start, limit);
			return pagedResult(results.getResults(), results.getTotal());
		} catch(Exception e) {
			logger.error("errer message", e);
			return pagedResult(new ArrayList<PayChannel>(), 0);
		}
	}

	@RequestMapping(value = "/payChannel/save", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, Object> savePayChannel(PayChannel payChannel) {
		try{
			// TODO Auto-generated method stub
			payChannelBiz.addPayChannel(payChannel);
			return success();
		} catch(Exception e) {
			logger.error("errer message", e);
			return fail();
		}
	}

	@RequestMapping(value = "/payChannel/update", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, Object> updatePayChannel(PayChannel payChannel) {
		try{
			// TODO Auto-generated method stub
			payChannelBiz.modifyPayChannel(payChannel);
			return success();
		} catch(Exception e) {
			logger.error("errer message", e);
			return fail();
		}
	}

	@RequestMapping(value = "/payChannel/del", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> delPayChannel(@RequestParam("id") long id) {
		try{
			// TODO Auto-generated method stub
			payChannelBiz.removePayChannel(id);
			return success();
		} catch(Exception e) {
			logger.error("errer message", e);
			return fail();
		}
	}

}