package com.dps.user;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dps.common.vo.PagedResult;
import com.dps.sp.dao.CoachDAO;
import com.dps.sp.vo.Coach;


@Service
public class CoachBiz {

	private static final  Logger logger = LoggerFactory.getLogger(CoachBiz.class);

	@Autowired
	private CoachDAO coachDAO;

	public PagedResult<Coach> listPagedCoachs(int offset, int limit) throws Exception {
		PagedResult<Coach> result = new PagedResult<Coach>();
		int total = coachDAO.getCoachTotal();
		List<Coach> prs = coachDAO.getPagedCoachs(offset, limit);
		result.setResults(prs);
		result.setTotal(total);
		return result;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void addCoach(Coach coach) throws Exception {
		// TODO Auto-generated method stub
		coachDAO.saveCoach(coach);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void modifyCoach(Coach coach) throws Exception {
		// TODO Auto-generated method stub
		coachDAO.updateCoach(coach);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void removeCoach(long id) throws Exception {
		// TODO Auto-generated method stub
		coachDAO.deleteCoach(id);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public Coach findCoach(long id) throws Exception {
		// TODO Auto-generated method stub
		Coach coach = coachDAO.getCoach(id);
		return coach;
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