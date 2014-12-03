package com.dps.sp.controller;

import com.dps.common.vo.DpsBizException;
import com.dps.common.vo.PagedResult;
import com.dps.common.web.BaseController;
import com.dps.sp.service.ServiceTypeBiz;
import com.dps.sp.domain.ServiceType;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Controller for Service type.
 */
@Controller
public class ServiceTypeController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(ServiceTypeController.class);

    @Autowired
    private ServiceTypeBiz serviceTypeBiz;

    @RequestMapping(value = "/servtype/paged/list", method = RequestMethod.GET)
    public
    @ResponseBody
    Map<String, Object> listPagedServType(@RequestParam("start") int start, @RequestParam("limit") int limit) {

        PagedResult<ServiceType> rs = serviceTypeBiz.getPagedServiceType(start, limit);

        logger.debug("Get paged list for ServiceType, result: {}", rs.getTotal());

        return pagedResult(rs.getResults(), rs.getTotal());
    }

    @RequestMapping(value = "/servtype/get", method = RequestMethod.GET)
    public
    @ResponseBody
    Map<String, Object> getServiceType(@RequestParam(value = "id", required = false) Integer id,
                                       @RequestParam(value = "name", required = false) String name) {


        if (id == null && (name == null || name.length() == 0)) {
            return listPagedServType(0, 15);
        } else {
            List<ServiceType> results = new ArrayList<ServiceType>();

            if (id != null && id.intValue() > 0) {
                ServiceType serviceType = serviceTypeBiz.findServiceTypeById(id.intValue());
                if (serviceType != null) {
                    results.add(serviceType);
                }
            }
            if (name != null && name.length() > 0) {
                List<ServiceType> serviceTypes = serviceTypeBiz.findServiceTypeByName(name);
                if (serviceTypes.size() > 0) {
                    results.addAll(serviceTypes);
                }
            }

            return pagedResult(results, results.size());
        }
    }


    @RequestMapping(value = "/servtype/save", method = RequestMethod.POST)
    public
    @ResponseBody
    Map<String, Object> saveServType(ServiceType serviceType) {
        try {
            if (serviceType.getServTypeName() == null || serviceType.getServTypeDesc() == null) {
                throw new DpsBizException("601", "Input Parameter isn't correct");
            }
            serviceTypeBiz.saveServiceType(serviceType);
            return success();
        } catch (DpsBizException e) {
            logger.error(e.getErrMsg(), e);
            return fail();
        }
    }

    @RequestMapping(value = "/servtype/update", method = RequestMethod.POST)
    public
    @ResponseBody
    Map<String, Object> updateSP(ServiceType serviceType) {
        try {
            serviceTypeBiz.updateServiceType(serviceType);
            return success();
        } catch (DpsBizException e) {
            logger.error(e.getErrMsg(), e);
            return fail();
        }
    }

    @RequestMapping(value = "/servtype/del", method = RequestMethod.GET)
    public
    @ResponseBody
    Map<String, Object> delSP(@RequestParam("id") int serviceTypeId) {
        try {
            serviceTypeBiz.deleteServiceType(serviceTypeId);
            return success();
        } catch (DpsBizException e) {
            logger.error(e.getErrMsg(), e);
            return fail();
        }
    }
}
