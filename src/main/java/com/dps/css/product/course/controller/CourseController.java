package com.dps.css.product.course.controller;

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
import com.dps.css.product.course.service.CourseBiz;
import com.dps.css.product.course.vo.Course;

@Controller
public class CourseController extends BaseController{

	private static final  Logger logger = LoggerFactory.getLogger(CourseController.class);

	@Autowired
	private CourseBiz courseBiz;

	
	@RequestMapping(value = "/course/get", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> findCourse(@RequestParam("id") long id) {
		try{
			// TODO Auto-generated method stub
			Course course = courseBiz.findCourse(id);
			return data(course);
		} catch(Exception e) {
			logger.error("errer message", e);
			return data(null);
		}
	}

	@RequestMapping(value = "/course/paged/list", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> listPagedCourses(@RequestParam("start") int start, @RequestParam("limit") int limit) {
		try{
			// TODO Auto-generated method stub
			PagedResult<Course> results = courseBiz.listPagedCourses(start, limit);
			return pagedResult(results.getResults(), results.getTotal());
		} catch(Exception e) {
			logger.error("errer message", e);
			return pagedResult(new ArrayList<Course>(), 0);
		}
	}

	@RequestMapping(value = "/course/save", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, Object> saveCourse(Course course) {
		try{
			// TODO Auto-generated method stub
			courseBiz.addCourse(course);
			return success();
		} catch(Exception e) {
			logger.error("errer message", e);
			return fail();
		}
	}

	@RequestMapping(value = "/course/update", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, Object> updateCourse(Course course) {
		try{
			// TODO Auto-generated method stub
			courseBiz.modifyCourse(course);
			return success();
		} catch(Exception e) {
			logger.error("errer message", e);
			return fail();
		}
	}

	@RequestMapping(value = "/course/del", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> delCourse(@RequestParam("id") long id) {
		try{
			// TODO Auto-generated method stub
			courseBiz.removeCourse(id);
			return success();
		} catch(Exception e) {
			logger.error("errer message", e);
			return fail();
		}
	}

}