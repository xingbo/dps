package com.dps.css.product.course.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dps.common.vo.PagedResult;
import com.dps.css.product.course.dao.CourseDAO;
import com.dps.css.product.course.vo.Course;

@Service
public class CourseBiz {

	private static final  Logger logger = LoggerFactory.getLogger(CourseBiz.class);

	@Autowired
	private CourseDAO courseDAO;

	public PagedResult<Course> listPagedCourses(int offset, int limit) throws Exception {
		PagedResult<Course> result = new PagedResult<Course>();
		int total = courseDAO.getCourseTotal();
		List<Course> prs = courseDAO.getPagedCourses(offset, limit);
		result.setResults(prs);
		result.setTotal(total);
		return result;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void addCourse(Course course) throws Exception {
		// TODO Auto-generated method stub
		courseDAO.saveCourse(course);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void modifyCourse(Course course) throws Exception {
		// TODO Auto-generated method stub
		courseDAO.updateCourse(course);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void removeCourse(long id) throws Exception {
		// TODO Auto-generated method stub
		courseDAO.deleteCourse(id);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public Course findCourse(long id) throws Exception {
		// TODO Auto-generated method stub
		Course course = courseDAO.getCourse(id);
		return course;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void exampleMethod() throws Exception {
		// TODO Auto-generated method stub
		try{
			// TODO implements biz
		} catch(Exception e) {
			// TODO Auto-generated method stub
			logger.error("errer message", e);
		}
	}

}