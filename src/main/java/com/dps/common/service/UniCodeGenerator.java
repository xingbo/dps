package com.dps.common.service;

import org.springframework.stereotype.Service;

import com.dps.common.vo.DpsBizException;

@Service
public class UniCodeGenerator {

	public static final int TYPE_USER = 1;
	public static final int TYPE_SERVICD = 2;
	public static final int TYPE_ORDER = 3;
	public static final int TYPE_PAY = 4;
	public static final int TYPE_SERVICE_USED = 5;

	public synchronized String geneteUniCode(int type) throws DpsBizException, Exception {
		return "";
	}

	public synchronized String geneteUniCode(int type, UniCodeIF unicode) throws DpsBizException, Exception {
		return "";
	}

	public static interface UniCodeIF {
		public void append(StringBuffer more);
	}
}
