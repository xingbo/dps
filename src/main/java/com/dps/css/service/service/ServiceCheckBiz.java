package com.dps.css.service.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dps.common.vo.DpsBizException;
import com.dps.css.service.vo.DPService;

@Service
public class ServiceCheckBiz {

	private static final Logger logger = LoggerFactory.getLogger(ServiceCheckBiz.class);

	@Transactional(propagation = Propagation.REQUIRED)
	public DPService checkService4Order(long serviceId) throws DpsBizException, Exception {
		// TODO Auto-generated method stub
		DPService service = null;
		return service;
	}

}
