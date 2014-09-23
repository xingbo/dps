package com.dps.css.product.court.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dps.common.vo.PagedResult;
import com.dps.css.product.court.dao.CourtDAO;
import com.dps.css.product.court.vo.Court;

@Service
public class CourtBiz {

	private static final  Logger logger = LoggerFactory.getLogger(CourtBiz.class);

	@Autowired
	private CourtDAO courtDAO;

	public PagedResult<Court> listPagedCourts(int offset, int limit) throws Exception {
		PagedResult<Court> result = new PagedResult<Court>();
		int total = courtDAO.getCourtTotal();
		List<Court> prs = courtDAO.getPagedCourts(offset, limit);
		result.setResults(prs);
		result.setTotal(total);
		return result;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void addCourt(Court court) throws Exception {
		// TODO Auto-generated method stub
		courtDAO.saveCourt(court);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void modifyCourt(Court court) throws Exception {
		// TODO Auto-generated method stub
		courtDAO.updateCourt(court);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void removeCourt(long id) throws Exception {
		// TODO Auto-generated method stub
		courtDAO.deleteCourt(id);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public Court findCourt(long id) throws Exception {
		// TODO Auto-generated method stub
		Court court = courtDAO.getCourt(id);
		return court;
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