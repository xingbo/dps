package com.dps.user.controller;

import java.util.ArrayList;
import java.util.Map;

import com.dps.user.vo.UserListVo;
import com.dps.user.vo.UserVo;
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
import com.dps.user.service.UserBiz;
import com.dps.user.domain.User;

@Controller
public class UserController extends BaseController{

	private static final  Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserBiz userBiz;
	
	//注册用户
	@RequestMapping(value = "/user/regist", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, Object> registeUser(User user) {
		try{
			// TODO Auto-generated method stub
			//PagedResult<UserListVo> results = userBiz.listPagedUsers(start, limit);
			return success();
		} catch(Exception e) {
			logger.error("errer message", e);
			return fail();
		}
	}
	
	//获取用户信息
	@RequestMapping(value = "/user/get", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> findUser(@RequestParam("userId") long userId) {
		try{
			// TODO Auto-generated method stub
			User user = userBiz.findUserById(userId);
			return data(user);
		} catch(Exception e) {
			logger.error("errer message", e);
			return data(null);
		}
	}

	
	
	@RequestMapping(value = "/user/paged/list", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> listPagedUsers(@RequestParam("start") int start, @RequestParam("limit") int limit) {
		try{
			// TODO Auto-generated method stub
			PagedResult<UserListVo> results = userBiz.listPagedUsers(start, limit);
			return pagedResult(results.getResults(), results.getTotal());
		} catch(Exception e) {
			logger.error("errer message", e);
			return pagedResult(new ArrayList<User>(), 0);
		}
	}

    @RequestMapping(value = "/cuser/list", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> listCUsers() {
        try{
            PagedResult<UserVo> results = userBiz.listCUsers();
            return pagedResult(results.getResults(), results.getTotal());
        } catch(Exception e) {
            logger.error("errer message", e);
            return pagedResult(new ArrayList<UserListVo>(), 0);
        }
    }

	@RequestMapping(value = "/user/save", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, Object> saveUser(User user, String pincode) {
		try{
			// TODO Auto-generated method stub
			userBiz.addUser(user, pincode);
			return success();
		} catch(Exception e) {
			logger.error("errer message", e);
			return fail();
		}
	}

	@RequestMapping(value = "/user/update", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, Object> updateUser(User user, String oldPassword) {
		try{
			// TODO Auto-generated method stub
			if(null!=user.getName())
				userBiz.modifyUserName(user.getId(), user.getName());
			if(null!=user.getPassword())
				userBiz.modifyUserPassword(user.getId(), oldPassword, user.getPassword());
			userBiz.modifyUserInfo(user);
			//TODO ������������������������������������������������������������������������������������������
			return success();
		} catch(Exception e) {
			logger.error("errer message", e);
			return fail();
		}
	}

	@RequestMapping(value = "/user/del", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> delUser(@RequestParam("id") long id) {
		try{
			// TODO Auto-generated method stub
			userBiz.removeUser(id);
			return success();
		} catch(Exception e) {
			logger.error("errer message", e);
			return fail();
		}
	}

}