package com.dps.msg.info.controller;

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
import com.dps.msg.info.service.MsgBiz;
import com.dps.msg.info.vo.Msg;

@Controller
public class MsgController extends BaseController{

	private static final  Logger logger = LoggerFactory.getLogger(MsgController.class);

	@Autowired
	private MsgBiz msgBiz;

	@RequestMapping(value = "/msg/get", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> findMsg(@RequestParam("id") long id) {
		try{
			// TODO Auto-generated method stub
			Msg msg = msgBiz.findMsg(id);
			return data(msg);
		} catch(Exception e) {
			logger.error("errer message", e);
			return data(null);
		}
	}

	@RequestMapping(value = "/msg/paged/list", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> listPagedMsgs(@RequestParam("start") int start, @RequestParam("limit") int limit) {
		try{
			// TODO Auto-generated method stub
			PagedResult<Msg> results = msgBiz.listPagedMsgs(start, limit);
			return pagedResult(results.getResults(), results.getTotal());
		} catch(Exception e) {
			logger.error("errer message", e);
			return pagedResult(new ArrayList<Msg>(), 0);
		}
	}

	@RequestMapping(value = "/msg/save", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, Object> saveMsg(Msg msg) {
		try{
			// TODO Auto-generated method stub
			msgBiz.addMsg(msg);
			return success();
		} catch(Exception e) {
			logger.error("errer message", e);
			return fail();
		}
	}

	@RequestMapping(value = "/msg/update", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, Object> updateMsg(Msg msg) {
		try{
			// TODO Auto-generated method stub
			msgBiz.modifyMsg(msg);
			return success();
		} catch(Exception e) {
			logger.error("errer message", e);
			return fail();
		}
	}

	@RequestMapping(value = "/msg/del", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> delMsg(@RequestParam("id") long id) {
		try{
			// TODO Auto-generated method stub
			msgBiz.removeMsg(id);
			return success();
		} catch(Exception e) {
			logger.error("errer message", e);
			return fail();
		}
	}

}