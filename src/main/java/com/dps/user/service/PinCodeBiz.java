package com.dps.user.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dps.common.util.commonUtil;
import com.dps.common.vo.DpsBizException;
import com.dps.user.dao.PinCodeDAO;
import com.dps.user.domain.PinCode;

@Service
public class PinCodeBiz {
	private static final Logger logger = LoggerFactory.getLogger(PinCodeBiz.class);

	@Autowired
	private PinCodeDAO pinCodeDAO;
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void addPinCode(String cellPhone) throws DpsBizException, Exception {
		// TODO Auto-generated method stub
		PinCode pinCode = new PinCode();
		//generate 6 digit pin code
		pinCode.setPinCode(commonUtil.getSixDigitRandomNum());
		pinCode.setStatus("0");
		//TODO: send message to cell phone
		//cellPhone.sendMessage(pinCode.getCellphone(), pinCode.getPinCode());
		pinCodeDAO.savePinCode(pinCode);
	}
}
