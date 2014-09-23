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
import com.dps.cos.order.service.ServicePinCodeBiz;
import com.dps.cos.order.vo.ServicePinCode;

@Controller
public class ServicePinCodeController extends BaseController{

	private static final  Logger logger = LoggerFactory.getLogger(ServicePinCodeController.class);

	@Autowired
	private ServicePinCodeBiz servicePinCodeBiz;

	@RequestMapping(value = "/servicePinCode/get", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> findServicePinCode(@RequestParam("id") long id) {
		try{
			// TODO Auto-generated method stub
			ServicePinCode servicePinCode = servicePinCodeBiz.findServicePinCode(id);
			return data(servicePinCode);
		} catch(Exception e) {
			logger.error("errer message", e);
			return data(null);
		}
	}

	@RequestMapping(value = "/servicePinCode/paged/list", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> listPagedServicePinCodes(@RequestParam("start") int start, @RequestParam("limit") int limit) {
		try{
			// TODO Auto-generated method stub
			PagedResult<ServicePinCode> results = servicePinCodeBiz.listPagedServicePinCodes(start, limit);
			return pagedResult(results.getResults(), results.getTotal());
		} catch(Exception e) {
			logger.error("errer message", e);
			return pagedResult(new ArrayList<ServicePinCode>(), 0);
		}
	}

	@RequestMapping(value = "/servicePinCode/save", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, Object> saveServicePinCode(ServicePinCode servicePinCode) {
		try{
			// TODO Auto-generated method stub
			servicePinCodeBiz.addServicePinCode(servicePinCode);
			return success();
		} catch(Exception e) {
			logger.error("errer message", e);
			return fail();
		}
	}

	@RequestMapping(value = "/servicePinCode/update", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, Object> updateServicePinCode(ServicePinCode servicePinCode) {
		try{
			// TODO Auto-generated method stub
			servicePinCodeBiz.modifyServicePinCode(servicePinCode);
			return success();
		} catch(Exception e) {
			logger.error("errer message", e);
			return fail();
		}
	}

	@RequestMapping(value = "/servicePinCode/del", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> delServicePinCode(@RequestParam("id") long id) {
		try{
			// TODO Auto-generated method stub
			servicePinCodeBiz.removeServicePinCode(id);
			return success();
		} catch(Exception e) {
			logger.error("errer message", e);
			return fail();
		}
	}

}