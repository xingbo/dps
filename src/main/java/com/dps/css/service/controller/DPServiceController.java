package com.dps.css.service.controller;

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
import com.dps.css.service.service.DPServiceBiz;
import com.dps.css.service.vo.DPService;

@Controller
public class DPServiceController extends BaseController{

	private static final  Logger logger = LoggerFactory.getLogger(DPServiceController.class);

	@Autowired
	private DPServiceBiz dPServiceBiz;

	@RequestMapping(value = "/dPService/get", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> findDPService(@RequestParam("id") long id) {
		try{
			// TODO Auto-generated method stub
			DPService dPService = dPServiceBiz.findDPService(id);
			return data(dPService);
		} catch(Exception e) {
			logger.error("errer message", e);
			return data(null);
		}
	}

	@RequestMapping(value = "/dPService/paged/list", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> listPagedDPServices(@RequestParam("start") int start, @RequestParam("limit") int limit) {
		try{
			// TODO Auto-generated method stub
			PagedResult<DPService> results = dPServiceBiz.listPagedDPServices(start, limit);
			return pagedResult(results.getResults(), results.getTotal());
		} catch(Exception e) {
			logger.error("errer message", e);
			return pagedResult(new ArrayList<DPService>(), 0);
		}
	}

	@RequestMapping(value = "/dPService/save", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, Object> saveDPService(DPService dPService) {
		try{
			// TODO Auto-generated method stub
			dPServiceBiz.addDPService(dPService);
			return success();
		} catch(Exception e) {
			logger.error("errer message", e);
			return fail();
		}
	}

	@RequestMapping(value = "/dPService/update", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, Object> updateDPService(DPService dPService) {
		try{
			// TODO Auto-generated method stub
			dPServiceBiz.modifyDPService(dPService);
			return success();
		} catch(Exception e) {
			logger.error("errer message", e);
			return fail();
		}
	}

	@RequestMapping(value = "/dPService/del", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> delDPService(@RequestParam("id") long id) {
		try{
			// TODO Auto-generated method stub
			dPServiceBiz.removeDPService(id);
			return success();
		} catch(Exception e) {
			logger.error("errer message", e);
			return fail();
		}
	}

}