package com.dps.msg.info.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dps.common.vo.PagedResult;
import com.dps.msg.info.dao.MsgDAO;
import com.dps.msg.info.vo.Msg;

@Service
public class MsgBiz {

	private static final  Logger logger = LoggerFactory.getLogger(MsgBiz.class);

	@Autowired
	private MsgDAO msgDAO;

	public PagedResult<Msg> listPagedMsgs(int offset, int limit) throws Exception {
		PagedResult<Msg> result = new PagedResult<Msg>();
		int total = msgDAO.getMsgTotal();
		List<Msg> prs = msgDAO.getPagedMsgs(offset, limit);
		result.setResults(prs);
		result.setTotal(total);
		return result;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void addMsg(Msg msg) throws Exception {
		// TODO Auto-generated method stub
		msgDAO.saveMsg(msg);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void modifyMsg(Msg msg) throws Exception {
		// TODO Auto-generated method stub
		msgDAO.updateMsg(msg);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void removeMsg(long id) throws Exception {
		// TODO Auto-generated method stub
		msgDAO.deleteMsg(id);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public Msg findMsg(long id) throws Exception {
		// TODO Auto-generated method stub
		Msg msg = msgDAO.getMsg(id);
		return msg;
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