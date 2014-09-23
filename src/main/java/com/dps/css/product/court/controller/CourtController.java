package com.dps.css.product.court.controller;

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
import com.dps.css.product.court.service.CourtBiz;
import com.dps.css.product.court.vo.Court;

@Controller
public class CourtController extends BaseController{

	private static final  Logger logger = LoggerFactory.getLogger(CourtController.class);

	@Autowired
	private CourtBiz courtBiz;

	@RequestMapping(value = "/court/get", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> findCourt(@RequestParam("id") long id) {
		try{
			// TODO Auto-generated method stub
			Court court = courtBiz.findCourt(id);
			return data(court);
		} catch(Exception e) {
			logger.error("errer message", e);
			return data(null);
		}
	}

	@RequestMapping(value = "/court/paged/list", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> listPagedCourts(@RequestParam("start") int start, @RequestParam("limit") int limit) {
		try{
			// TODO Auto-generated method stub
			PagedResult<Court> results = courtBiz.listPagedCourts(start, limit);
			return pagedResult(results.getResults(), results.getTotal());
		} catch(Exception e) {
			logger.error("errer message", e);
			return pagedResult(new ArrayList<Court>(), 0);
		}
	}

	@RequestMapping(value = "/court/save", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, Object> saveCourt(Court court) {
		try{
			// TODO Auto-generated method stub
			courtBiz.addCourt(court);
			return success();
		} catch(Exception e) {
			logger.error("errer message", e);
			return fail();
		}
	}

	@RequestMapping(value = "/court/update", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, Object> updateCourt(Court court) {
		try{
			// TODO Auto-generated method stub
			courtBiz.modifyCourt(court);
			return success();
		} catch(Exception e) {
			logger.error("errer message", e);
			return fail();
		}
	}

	@RequestMapping(value = "/court/del", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> delCourt(@RequestParam("id") long id) {
		try{
			// TODO Auto-generated method stub
			courtBiz.removeCourt(id);
			return success();
		} catch(Exception e) {
			logger.error("errer message", e);
			return fail();
		}
	}

}