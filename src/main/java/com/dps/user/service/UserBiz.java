package com.dps.user.service;

import java.util.List;

import com.dps.user.vo.UserListVo;
import com.dps.user.vo.UserVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dps.common.enums.ErrorCodeEnum;
import com.dps.common.util.MD5EnDeCryptUtil;
import com.dps.common.vo.DpsBizException;
import com.dps.common.vo.PagedResult;
import com.dps.user.dao.PinCodeDAO;
import com.dps.user.dao.UserMapper;
import com.dps.user.domain.User;


@Service
public class UserBiz {

	private static final Logger logger = LoggerFactory.getLogger(UserBiz.class);

	@Autowired
	private UserMapper userMapper;
	@Autowired
	private PinCodeDAO pinCodeDAO;


	public PagedResult<UserListVo> listPagedUsers(int offset, int limit) throws DpsBizException, Exception {
		PagedResult<UserListVo> result = new PagedResult<UserListVo>();
		int total = userMapper.getAllUserCount();
		List<UserListVo> prs = userMapper.getPagedUsers(offset, limit);
		result.setResults(prs);
		result.setTotal(total);
		return result;
	}

    public PagedResult<UserVo> listCUsers() throws DpsBizException, Exception {
        PagedResult<UserVo> result = new PagedResult<UserVo>();
        List<UserVo> users = userMapper.getCUsers();
        result.setResults(users);
        result.setTotal(users.size());
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED)
	public void addUser(User user, String pinCode) throws DpsBizException, Exception {
		// TODO Auto-generated method stub
		if(null == user || null == pinCode){
			throw new DpsBizException(ErrorCodeEnum.USER_EXISTEDPHONENUM.getId(), "user object is null");
		}
			
//		if(null != userMapper.findUserByName(user.getName())) {
//            throw new DpsBizException(ErrorCodeEnum.USER_EXISTED.getId(), "用户名已存在！");
//        }
		//一个手机号码可以注册多个角色的用户
//		if(null != userDAO.findUserByPhone(user.getPhone(), user.getType()))
//			throw new DpsBizException(ErrorCodeEnum.USER_EXISTEDPHONENUM.getId(), "手机号已存在！");

		if(null == user.getName())
			throw new DpsBizException(ErrorCodeEnum.USER_INVALID_DATA.getId(), "用户名不能为空");
		if(null == user.getPassword())
			throw new DpsBizException(ErrorCodeEnum.USER_INVALID_DATA.getId(), "密码不能为空");
		if(null == user.getType())
			throw new DpsBizException(ErrorCodeEnum.USER_INVALID_DATA.getId(), "用户类型不能为空");
		if(null == user.getPhone())
			throw new DpsBizException(ErrorCodeEnum.USER_INVALID_DATA.getId(), "电话不能为空");
//		if(null == user.getCity())
//			throw new DpsBizException(ErrorCodeEnum.USER_INVALID_DATA.getId(), "所属城市不能为空");
//		if(null == user.getAreas())
//			throw new DpsBizException(ErrorCodeEnum.USER_INVALID_DATA.getId(), "所属区域不能为空");
		//consumer the pin code
		pinCodeDAO.updatePinCodeToInactive(pinCode, user.getPhone());
		//encrypt the password
		user.setPassword(MD5EnDeCryptUtil.enDeCrypt(user.getPassword()));
		//init creation and update date
//		user.setCreateDate(DateUtil.getCurrentDate());
//		user.setUpdateDate(user.getCreateDate());
//		userMapper.saveUser(user);
		//如果是消费者，创建一条消费者详细信息数据
//		if(user.getType()==UserTypeEnum.CONSUMER.getId()){
//			ConsumerDetails consumerDetails = new ConsumerDetails();
//			consumerDetails.setUserId(user.getId());
//			consumerDetails.setCreateDate(DateUtil.getCurrentDate());
//			consumerDetails.setUpdateDate(consumerDetails.getCreateDate());
//			customerDetailsDao.saveCustomerUserDetails(consumerDetails);
//		}else if(user.getType() == UserTypeEnum.COACH.getId()){
//			CoachDetails coach = new CoachDetails();
//			coach.setUserId(user.getId());
//			coach.setCreateDate(DateUtil.getCurrentDate());
//			coach.setUpdateDate(coach.getCreateDate());
//			pSProviderDetailsDAO.savePSProviderUserDetails(coach);
//		}else if(user.getType() == UserTypeEnum.ClUBANDCOURT.getId()){
//			ClubCourtDetails clubCourtDetails = new ClubCourtDetails();
//			clubCourtDetails.setUserId(user.getId());
//			clubCourtDetails.setCreateDate(DateUtil.getCurrentDate());
//			clubCourtDetails.setUpdateDate(clubCourtDetails.getUpdateDate());
//			clubCourtDetailsDAO.saveClubCourtUserDetails(clubCourtDetails);
//		}
		
			
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void modifyUserName(long userId, String newUserName) throws DpsBizException, Exception {
		// TODO Auto-generated method stub
//		User user = userDAO.getUser(userId);
//		if(user==null)
//			throw new DpsBizException(ErrorCodeEnum.USER_UNEXISTED.getId(), "用户不存在");
//		user.setName(newUserName);
//		userDAO.updateUser(user);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void modifyUserPassword(long userId, String oldPassword,  String newPassword) throws DpsBizException, Exception {
		// TODO Auto-generated method stub
//		User user = userDAO.getUser(userId);
//		if(user==null)
//			throw new DpsBizException(ErrorCodeEnum.USER_UNEXISTED.getId(), "用户不存在");
//		if(!MD5EnDeCryptUtil.enDeCrypt(user.getPassword()).equalsIgnoreCase(oldPassword))
//			throw new DpsBizException(ErrorCodeEnum.USER_INVALIDPASSWORD.getId(), "密码不正确！");
//		user.setPassword(MD5EnDeCryptUtil.enDeCrypt(newPassword));
//		userDAO.updateUser(user);
	}
	

	@Transactional(propagation = Propagation.REQUIRED)
	public void modifyUserInfo(User user) throws DpsBizException, Exception {
//		User originalUser = userDAO.getUser(user.getId());
//		if(originalUser==null)
//			throw new DpsBizException(ErrorCodeEnum.USER_UNEXISTED.getId(), "用户不存在");
//		if(null != user.getAreas())
//			originalUser.setAreas(user.getAreas());
//		if(null != user.getCity())
//			originalUser.setCity(user.getCity());
//		//如果用户修改了手机号码，则用户需要用新的手机号码登陆
//		if(null != user.getPhone())
//			originalUser.setPhone(user.getPhone());
//		if(null != user.getBirthday())
//			originalUser.setBirthday(user.getBirthday());
//		userDAO.updateUser(originalUser);
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void removeUser(long id) throws DpsBizException, Exception {
		// TODO Auto-generated method stub
	/*	User user = userDAO.getUser(id);
		if(UserTypeEnum.CONSUMER.getId().equalsIgnoreCase(user.getType())){
			//check if this user still have un-settled orders.
			if(order.hasUnSettledOrders(id))
				throw new DpsBizException(errorcode, "有已支付但是未完成的订单");
			userDAO.updateUserToInactive(id);
			
		}else if(UserTypeEnum.COACH.getId().equalsIgnoreCase(user.getType())){
			//check if this user still have un-settled orders
			//purchase order
			if(orderForbuy.hasUnSettledOrders(id))
				throw new DpsBizException(errorcode, "有已支付但是未完成的订单");
			//selling order
			if(orderForCourse.hasUnSettledOrders(id))
				throw new DpsBizException(errorcode, "有已支付的课程");
			
			userDAO.updateUserToInactive(id);	
		}else if(UserTypeEnum.ClUBANDCOURT.getId().equalsIgnoreCase(user.getType())){
			//selling order
			if(orderForCourseOrCourt.hasUnSettledOrders(id))
				throw new DpsBizException(errorcode, "有已支付的订单");
			userDAO.updateUserToInactive(id);
		}
		
		throw new DpsBizException(errorcode, "用户类型错误");*/
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public User findUserById(long id) throws DpsBizException, Exception {
		// TODO Auto-generated method stub
//		User user = userDAO.getUser(id);
		return null;
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public User findUserByPhoneOrName(String phoneOrName) throws DpsBizException, Exception {
		// TODO Auto-generated method stub
//		User user = userDAO.getUserByPhoneOrName(phoneOrName);
		return null;
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void exampleMethod() throws DpsBizException, Exception {
		// TODO Auto-generated method stub
		try {
			// TODO implements biz
		} catch (Exception e) {
			// TODO Auto-generated method stub
			logger.error("errer message", e);
		}
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public User checkUser4Order(long userId) throws DpsBizException, Exception {
		// TODO Auto-generated method stub
		User user = null;
		return user;
	}

}