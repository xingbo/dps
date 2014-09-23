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
import com.dps.cos.pay.service.PayInfoBiz;
import com.dps.cos.pay.vo.PayInfo;

@Controller
public class PayInfoController extends BaseController{

	private static final  Logger logger = LoggerFactory.getLogger(PayInfoController.class);

	@Autowired
	private PayInfoBiz payInfoBiz;

	@RequestMapping(value = "/payInfo/get", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> findPayInfo(@RequestParam("id") long id) {
		try{
			// TODO Auto-generated method stub
			PayInfo payInfo = payInfoBiz.findPayInfo(id);
			return data(payInfo);
		} catch(Exception e) {
			logger.error("errer message", e);
			return data(null);
		}
	}

	@RequestMapping(value = "/payInfo/paged/list", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> listPagedPayInfos(@RequestParam("start") int start, @RequestParam("limit") int limit) {
		try{
			// TODO Auto-generated method stub
			PagedResult<PayInfo> results = payInfoBiz.listPagedPayInfos(start, limit);
			return pagedResult(results.getResults(), results.getTotal());
		} catch(Exception e) {
			logger.error("errer message", e);
			return pagedResult(new ArrayList<PayInfo>(), 0);
		}
	}

	@RequestMapping(value = "/payInfo/save", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, Object> savePayInfo(PayInfo payInfo) {
		try{
			// TODO Auto-generated method stub
			//payInfoBiz.addPayInfo(payInfo);
			return success();
		} catch(Exception e) {
			logger.error("errer message", e);
			return fail();
		}
	}

	@RequestMapping(value = "/payInfo/update", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, Object> updatePayInfo(PayInfo payInfo) {
		try{
			// TODO Auto-generated method stub
			payInfoBiz.modifyPayInfo(payInfo);
			return success();
		} catch(Exception e) {
			logger.error("errer message", e);
			return fail();
		}
	}

	@RequestMapping(value = "/payInfo/del", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> delPayInfo(@RequestParam("id") long id) {
		try{
			// TODO Auto-generated method stub
			payInfoBiz.removePayInfo(id);
			return success();
		} catch(Exception e) {
			logger.error("errer message", e);
			return fail();
		}
	}

}