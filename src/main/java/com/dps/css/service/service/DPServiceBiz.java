package com.dps.css.service.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dps.common.vo.PagedResult;
import com.dps.css.service.dao.DPServiceDAO;
import com.dps.css.service.vo.DPService;

@Service
public class DPServiceBiz {

	private static final  Logger logger = LoggerFactory.getLogger(DPServiceBiz.class);

	@Autowired
	private DPServiceDAO dPServiceDAO;

	public PagedResult<DPService> listPagedDPServices(int offset, int limit) throws Exception {
		PagedResult<DPService> result = new PagedResult<DPService>();
		int total = dPServiceDAO.getDPServiceTotal();
		List<DPService> prs = dPServiceDAO.getPagedDPServices(offset, limit);
		result.setResults(prs);
		result.setTotal(total);
		return result;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void addDPService(DPService dPService) throws Exception {
		// TODO Auto-generated method stub
		dPServiceDAO.saveDPService(dPService);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void modifyDPService(DPService dPService) throws Exception {
		// TODO Auto-generated method stub
		dPServiceDAO.updateDPService(dPService);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void removeDPService(long id) throws Exception {
		// TODO Auto-generated method stub
		dPServiceDAO.deleteDPService(id);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public DPService findDPService(long id) throws Exception {
		// TODO Auto-generated method stub
		DPService dPService = dPServiceDAO.getDPService(id);
		return dPService;
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