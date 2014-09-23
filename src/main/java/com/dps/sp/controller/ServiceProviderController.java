package com.dps.sp.controller;

import java.util.ArrayList;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.dps.common.enums.ErrorCodeEnum;
import com.dps.common.enums.ServiceProviderCategoryEnum;
import com.dps.common.enums.StatusEnum;
import com.dps.common.vo.DpsBizException;
import com.dps.sp.domain.ServiceType;
import com.dps.sp.service.ServiceTypeBiz;
import com.dps.sp.vo.ServiceProviderDetailVo;
import com.dps.sp.vo.ServiceProviderVo;
import com.dps.user.service.UserBiz;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.dps.common.vo.PagedResult;
import com.dps.common.web.BaseController;
import com.dps.sp.service.ServiceProviderBiz;
import com.dps.sp.domain.ServiceProvider;
import com.dps.user.domain.User;

@Controller
public class ServiceProviderController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(ServiceProviderController.class);

    @Autowired
    private ServiceProviderBiz serviceProviderBiz;

    @Autowired
    private ServiceTypeBiz serviceTypeBiz;

    @Autowired
    private UserBiz userBiz;


	@RequestMapping(value = "/sp/paged/list", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> listPagedSP(@RequestParam("start") int start, @RequestParam("limit") int limit) {
		try{
			PagedResult<ServiceProviderVo> results = serviceProviderBiz.listPagedSP(start, limit);
			return pagedResult(results.getResults(), results.getTotal());
		} catch(Exception e) {
			logger.error("errer message", e);
			return pagedResult(new ArrayList<ServiceProviderVo>(), 0);
		}
	}

	@RequestMapping(value = "/sp/get", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> findSPById(@RequestParam(value = "spId")Integer spId) {
		try{
            if (spId != null) {
                ServiceProviderDetailVo sp = serviceProviderBiz.findSPById(spId.intValue());
                if (sp != null) {
                    return data(sp);
                }
            }
            return data(null);
		} catch(Exception e) {
			logger.error("errer message", e);
			return data(null);
		}
	}

	@RequestMapping(value = "/sp/save", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, Object> saveSP(@RequestParam("spName") String spName,
                               @RequestParam("spCategory") Integer spCategory,
                               @RequestParam("spTypes") Integer spTypes,
                               @RequestParam("spOwner") Integer spOwner,
                               @RequestParam("spCity") String spCity,
                               @RequestParam("spDistrict") String spDistrict,
                               @RequestParam("spAddress") String spAddress,
                               @RequestParam("spPhone") String spPhone,
                               @RequestParam("servicesArea") String servicesArea,
                               @RequestParam("spAdvtImage") String spAdvtImage,
                               @RequestParam("spCertImage") String spCertImage,
                               @RequestParam("spDescription") String spDescription) {
		try{
            // data validity check
            if(spName == null) {
                throw new DpsBizException(ErrorCodeEnum.SP_INVALID_DATA.getId(), "请起一个霸气的名字");
            }
            if(spCategory == null || spCategory.intValue() == 0) {
                throw new DpsBizException(ErrorCodeEnum.SP_INVALID_DATA.getId(), "请选择服务种类");
            }
            if(spTypes == null || spCategory.intValue() == 0) {
                throw new DpsBizException(ErrorCodeEnum.SP_INVALID_DATA.getId(), "请选择服务所属的活动种类");
            }
            if(spOwner == null || spCategory.intValue() == 0) {
                throw new DpsBizException(ErrorCodeEnum.SP_INVALID_DATA.getId(), "请选择服务所属人");
            }
            if(spCity == null) {
                throw new DpsBizException(ErrorCodeEnum.SP_INVALID_DATA.getId(), "请输入城市");
            }
            if(spDistrict == null) {
                throw new DpsBizException(ErrorCodeEnum.SP_INVALID_DATA.getId(), "请输入区");
            }
            if(spAddress == null) {
                throw new DpsBizException(ErrorCodeEnum.SP_INVALID_DATA.getId(), "请输入地址");
            }
            if(spPhone == null) {
                throw new DpsBizException(ErrorCodeEnum.SP_INVALID_DATA.getId(), "请输入联系电话");
            }
            if(servicesArea == null) {
                throw new DpsBizException(ErrorCodeEnum.SP_INVALID_DATA.getId(), "请输入服务地区");
            }
            if(spDescription == null) {
                throw new DpsBizException(ErrorCodeEnum.SP_INVALID_DATA.getId(), "请输入简单描述");
            }

            // transfer json to vo
            ServiceProvider newSP = new ServiceProvider();
            newSP.setSpName(spName);
            newSP.setSpCategory(ServiceProviderCategoryEnum.getEnumById(spCategory));
            newSP.setSpCity(spCity);
            newSP.setSpDistrict(spDistrict);
            newSP.setSpAddress(spAddress);
            newSP.setSpPhone(spPhone);
            newSP.setSpServArea(servicesArea);
            newSP.setSpAdvtImage(spAdvtImage);
            newSP.setSpCertImage(spCertImage);
            newSP.setSpDescription(spDescription);
            newSP.setCreateDate(new Date(System.currentTimeMillis()));
            newSP.setUpdateDate(new Date(System.currentTimeMillis()));
            // new sp is always CREATE_SP(200) status
            newSP.setSpStatus(StatusEnum.CREATE_SP);
            // ugly
            User tmpUser = new User();
            tmpUser.setId(spOwner);
            newSP.setOwner(tmpUser);
            // ugly
            ServiceType tmpST = new ServiceType();
            tmpST.setServTypeId(spTypes);
            List<ServiceType> tmpSTs = new ArrayList<ServiceType>();
            tmpSTs.add(tmpST);
            newSP.setAvailableServiceTypes(tmpSTs);

            // call the service method to persist
			serviceProviderBiz.addSP(newSP);
			return success();
		} catch(Exception e) {
			logger.error("errer message", e);
			return fail();
		}
	}
//
//	@RequestMapping(value = "/sp/update", method = RequestMethod.POST)
//	public @ResponseBody
//	Map<String, Object> updateSP(SPInfo spInfo) {
//		try{
//			// TODO Auto-generated method stub
//			serviceProviderBiz.modifySP(spInfo);
//			return success();
//		} catch(Exception e) {
//			logger.error("errer message", e);
//			return fail();
//		}
//	}
//
//	@RequestMapping(value = "/sp/del", method = RequestMethod.GET)
//	public @ResponseBody
//	Map<String, Object> delSP(@RequestParam("spID") long spID) {
//		try{
//			// TODO Auto-generated method stub
//			serviceProviderBiz.removeSP(spID);
//			return success();
//		} catch(Exception e) {
//			logger.error("errer message", e);
//			return fail();
//		}
//	}
}
