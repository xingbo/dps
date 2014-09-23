package com.dps.cos.order.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dps.common.vo.PagedResult;
import com.dps.cos.order.dao.ServicePinCodeDAO;
import com.dps.cos.order.vo.ServicePinCode;

@Service
public class ServicePinCodeBiz {

	private static final  Logger logger = LoggerFactory.getLogger(ServicePinCodeBiz.class);

	@Autowired
	private ServicePinCodeDAO servicePinCodeDAO;

	public PagedResult<ServicePinCode> listPagedServicePinCodes(int offset, int limit) throws Exception {
		PagedResult<ServicePinCode> result = new PagedResult<ServicePinCode>();
		int total = servicePinCodeDAO.getServicePinCodeTotal();
		List<ServicePinCode> prs = servicePinCodeDAO.getPagedServicePinCodes(offset, limit);
		result.setResults(prs);
		result.setTotal(total);
		return result;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void addServicePinCode(ServicePinCode servicePinCode) throws Exception {
		// TODO Auto-generated method stub
		servicePinCodeDAO.saveServicePinCode(servicePinCode);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void modifyServicePinCode(ServicePinCode servicePinCode) throws Exception {
		// TODO Auto-generated method stub
		servicePinCodeDAO.updateServicePinCode(servicePinCode);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void removeServicePinCode(long id) throws Exception {
		// TODO Auto-generated method stub
		servicePinCodeDAO.deleteServicePinCode(id);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public ServicePinCode findServicePinCode(long id) throws Exception {
		// TODO Auto-generated method stub
		ServicePinCode servicePinCode = servicePinCodeDAO.getServicePinCode(id);
		return servicePinCode;
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