package com.dps.sp.service;

import com.dps.common.enums.ErrorCodeEnum;
import com.dps.common.enums.ServiceProviderCategoryEnum;
import com.dps.common.enums.StatusEnum;
import com.dps.common.enums.UserTypeEnum;
import com.dps.common.util.DateUtil;
import com.dps.common.vo.DpsBizException;
import com.dps.common.vo.PagedResult;
import com.dps.sp.dao.ServiceProviderMapper;
import com.dps.sp.domain.ServiceProvider;
import com.dps.sp.domain.ServiceType;
import com.dps.sp.vo.ServiceProviderDetailVo;
import com.dps.sp.vo.ServiceProviderVo;
import com.dps.user.dao.UserMapper;
import com.dps.user.domain.User;
import com.dps.user.vo.UserVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class ServiceProviderBiz {

    private static final Logger logger = LoggerFactory.getLogger(ServiceProviderBiz.class);

    @Autowired
    private ServiceProviderMapper serviceProviderMapper;

    @Autowired
    private UserMapper userMapper;


    public PagedResult<ServiceProviderVo> listPagedSP(int offset, int limit) throws Exception {
        PagedResult<ServiceProviderVo> result = new PagedResult<ServiceProviderVo>();
        int total = serviceProviderMapper.getTotalSPNum();
        List<ServiceProviderVo> prs = serviceProviderMapper.getPagedSP(offset, limit);
        result.setResults(prs);
        result.setTotal(total);
        return result;
    }

	public ServiceProviderDetailVo findSPById(int spId){
		return serviceProviderMapper.findSPById(spId);
	}


    @Transactional(propagation = Propagation.REQUIRED)
    public void addSP(ServiceProvider sp) throws Exception {
        // check user info
        UserVo user = userMapper.findUserById(sp.getOwner().getId());
        if (null == user) {
            throw new DpsBizException(ErrorCodeEnum.SP_INVALID_DATA.getId(), "找不到用户，请检查用户ID");
        }
        if (user.getType() == UserTypeEnum.COACH && sp.getSpCategory() != ServiceProviderCategoryEnum.COACH_SERV_PROVIDER) {
            throw new DpsBizException(ErrorCodeEnum.SP_INVALID_DATA.getId(), "教练用户只能创建教练sp");
        }
        if (user.getType() == UserTypeEnum.ClUBANDCOURT && sp.getSpCategory() == ServiceProviderCategoryEnum.COACH_SERV_PROVIDER) {
            throw new DpsBizException(ErrorCodeEnum.SP_INVALID_DATA.getId(), "俱乐部场地用户不能创建教练sp");
        }

        // save sp only
        boolean retVal1 = serviceProviderMapper.saveServProvider(sp);
        int spId = serviceProviderMapper.getLatestId();

        // save sp-user mapping
        boolean retVal2 = serviceProviderMapper.saveSP2UserRelation(user.getId(), spId, 1, 1, sp.getCreateDate(), sp.getUpdateDate());

        // save sp-st mapping
        // todo try batch import?
        for (ServiceType st : sp.getAvailableServiceTypes()) {
            serviceProviderMapper.saveSP2STRelation(spId, st.getServTypeId(), 1, sp.getCreateDate(), sp.getUpdateDate());
        }

        if (retVal1 && retVal2) {
            logger.debug("New ServiceProvider[{}] is saved successfully.");
        } else {
            throw new DpsBizException("502", "ServiceProvider is not saved.");
        }
    }


//	@Transactional(propagation = Propagation.REQUIRED)
//	public void modifySP(SPInfo sp) throws Exception {
//
//		ServiceProvider originalServiceProvider = serviceProviderMapper.findSP(sp.getSpId());
//
//		if(sp.getSpType() == SPTypeEnum.COACHSP.getId()){
//			coachSPBiz.modifyCoachSP(sp);
//			if(null != sp.getName())
//				originalServiceProvider.setName(sp.getName());
//			if(null != sp.getPhone())
//				originalServiceProvider.setPhone(sp.getPhone());
//			if(null != sp.getAddress())
//				originalServiceProvider.setAddress(sp.getAddress());
//			if(null!=sp.getCity())
//				originalServiceProvider.setCity(sp.getCity());
//			originalServiceProvider.setUpdateDate(DateUtil.getCurrentDate());
//			serviceProviderMapper.updateSP(originalServiceProvider);
//		}else{
//			//检查不可修改的信息
//			if(null!=sp.getServiceTypes()){
//				throw new DpsBizException(ErrorCodeEnum.SP_INVALID_DATA.getId(), "服务类型信息不能修改，可以新建一个场地或俱乐部！");
//			}
//			if(null!=sp.getCity()){
//				throw new DpsBizException(ErrorCodeEnum.SP_INVALID_DATA.getId(), "所在城市信息不能修改，可以新建一个场地或俱乐部");
//			}
//			if(null != sp.getAddress()){
//				originalServiceProvider.setAddress(sp.getAddress());
//				//待审核，审核中，重新审核状态不能修改
//				if(originalServiceProvider.getStatus() == StatusEnum.WAIT_APPROVE_SP.getId()||
//						originalServiceProvider.getStatus() == StatusEnum.APPROVING_SP.getId() ||
//								originalServiceProvider.getStatus() == StatusEnum.REAPPROVING_SP.getId())
//					throw new DpsBizException(ErrorCodeEnum.SP_STATUS_NOT_RIGHT.getId(), "SP当前状态为"+StatusEnum.getEnumByID(originalServiceProvider.getStatus())+", 信息不能修改");
//				//活跃状态下，或者冻结状态下，如果有订单，信息也不能修改
//				if(originalServiceProvider.getStatus() == StatusEnum.ACTIVE_SP.getId() ||
//						originalServiceProvider.getStatus() == StatusEnum.FROZEN_SP.getId()){
//					/*if(有未完成的订单)
//						throw new DpsBizException(ErrorCodeEnum.SP_STATUS_NOT_RIGHT.getId(), "有未完成得订单，信息不能修改");*/
//				}
//				//需要重新审批
//				originalServiceProvider.setStatus(StatusEnum.REAPPROVING_SP.getId());
//				//其他随便改
//				if(null != sp.getName())
//					originalServiceProvider.setName(sp.getName());
//				if(null != sp.getPhone())
//					originalServiceProvider.setPhone(sp.getPhone());
//				serviceProviderMapper.updateSP(originalServiceProvider);
//				//修改sp具体的信息
//				if(sp.getSpType() == SPTypeEnum.COURTSP.getId())
//					courtSPBiz.modifyCourtSP(sp);
//				if(sp.getSpType() == SPTypeEnum.ClUBSP.getId())
//					clubSPBiz.modifyClubSP(sp);
//			}
//		}
//	}
//
//
//	@Transactional(propagation = Propagation.REQUIRED)
//	public void removeSP(long spID) throws Exception {
//		ServiceProvider originalServiceProvider = serviceProviderMapper.findSP(spID);
//		if(originalServiceProvider.getStatus() == StatusEnum.ACTIVE_SP.getId())
//			throw new DpsBizException(ErrorCodeEnum.SP_STATUS_NOT_RIGHT.getId(), "请先冻结，并处理未完成的订单");
//		if(originalServiceProvider.getStatus() == StatusEnum.FROZEN_SP.getId())
//			//if(//仍然有未完成的订单)
//				throw new DpsBizException(ErrorCodeEnum.SP_STATUS_NOT_RIGHT.getId(), "删除之前，请先处理未完成的订单");
//		//首先删除sp下的所有管理者关系
//		userToSPMapDAO.deleteUserToSPMap(spID);
//		//删除具体sp信息
//		if(originalServiceProvider.getSpType() == SPTypeEnum.COACHSP.getId())
//			coachSPBiz.removeCoachSP(spID);
//		else if(originalServiceProvider.getSpType() == SPTypeEnum.COURTSP.getId())
//			courtSPBiz.removeCourtSP(spID);
//		else if(originalServiceProvider.getSpType() == SPTypeEnum.ClUBSP.getId())
//			clubSPBiz.removeClubSP(spID);
//		//删除sp
//		serviceProviderMapper.updateSPStatusByID(spID, StatusEnum.INACTIVE_SP.getId(), DateUtil.getCurrentDate());
//	}

	/*
	 * 1. 如果场地sp是活跃状态，不能删除。
	 * 2。 冻结场地
	 * 3。处理所有未完成的订单
	 * 4。删除
	 * 5。注：如果场地是在新建、待审批、审批中、重新审批状态，其下肯定没有未完成的订单，所以可以直接删除
	 * //冻结这个sp，这样它可以有机会修改一些重要信息，然后重新审批,或者运营人员冻结一些非法的sp
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void superiorSP(Integer spId, StatusEnum nextStatus) throws Exception {
//        ServiceProvider originalServiceProvider = serviceProviderMapper.findSP(spId);
//        if (originalServiceProvider.getStatus() != StatusEnum.ACTIVE_SP.getId()) {
//            throw new DpsBizException(ErrorCodeEnum.SP_STATUS_NOT_RIGHT.getId(),
//                    "SP当前状态为" + StatusEnum.getEnumByID(originalServiceProvider.getStatus()) + ", 无法冻结");
//        }
		serviceProviderMapper.updateServProviderStatus(spId, nextStatus);
	}


}
