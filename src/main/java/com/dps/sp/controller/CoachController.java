package com.dps.sp.coach.controller;

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
import com.dps.sp.coach.service.CoachBiz;
import com.dps.sp.coach.vo.Coach;

@Controller
public class CoachController extends BaseController{

	private static final  Logger logger = LoggerFactory.getLogger(CoachController.class);

	@Autowired
	private CoachBiz coachBiz;

	
	//获取所有的教练信息
	@RequestMapping(value = "/coach/paged/list", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, Object> listPagedCoachs(@RequestParam("start") int start, @RequestParam("limit") int limit) {
		try{
			// TODO Auto-generated method stub
			//PagedResult<Coach> results = coachBiz.listPagedCoachs(start, limit);
			PagedResult<Coach> results = new PagedResult<Coach>();
			
			Coach c = new Coach();
			c.setId(1);
			c.setAddress("������������");
			c.setAge(45);
			c.setLevel(3);
			c.setName("���������");
			c.setPhone("13812345678");
			c.setType(1);
			results.addResult(c);
			
			c = new Coach();
			c.setId(1);
			c.setAddress("������������");
			c.setAge(34);
			c.setLevel(5);
			c.setName("���������");
			c.setPhone("18612345678");
			c.setType(2);
			results.addResult(c);
			
			return pagedResult(results.getResults(), results.getTotal());
		} catch(Exception e) {
			logger.error("errer message", e);
			return pagedResult(new ArrayList<Coach>(), 0);
		}
	}
 
	//某教练的个人详细信息
	@RequestMapping(value = "/coach/get", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> findCoach(@RequestParam("coachId") long coachId) {
		try{
			// TODO Auto-generated method stub
			Coach coach = coachBiz.findCoach(id);
			return data(coach);
		} catch(Exception e) {
			logger.error("errer message", e);
			return data(null);
		}
	}

	
	@RequestMapping(value = "/coach/save", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, Object> saveCoach(Coach coach) {
		try{
			// TODO Auto-generated method stub
			coachBiz.addCoach(coach);
			return success();
		} catch(Exception e) {
			logger.error("errer message", e);
			return fail();
		}
	}

	@RequestMapping(value = "/coach/update", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, Object> updateCoach(Coach coach) {
		try{
			// TODO Auto-generated method stub
			coachBiz.modifyCoach(coach);
			return success();
		} catch(Exception e) {
			logger.error("errer message", e);
			return fail();
		}
	}

	@RequestMapping(value = "/coach/del", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> delCoach(@RequestParam("id") long id) {
		try{
			// TODO Auto-generated method stub
			coachBiz.removeCoach(id);
			return success();
		} catch(Exception e) {
			logger.error("errer message", e);
			return fail();
		}
	}

}