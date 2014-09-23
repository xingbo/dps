package com.dps.sp.service;

import com.dps.common.vo.DpsBizException;
import com.dps.common.vo.PagedResult;
import com.dps.sp.dao.ServiceTypeMapper;
import com.dps.sp.domain.ServiceType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service for Service Type.
 */
@Service
public class ServiceTypeBiz {

    private static final Logger logger = LoggerFactory.getLogger(ServiceTypeBiz.class);

    @Autowired
    ServiceTypeMapper serviceTypeMapper;

    public int getTotalCount() {
        return serviceTypeMapper.getAllServTypeCount();
    }

    public PagedResult<ServiceType> getPagedServiceType(int offset, int limit) {

        PagedResult<ServiceType> result = new PagedResult<ServiceType>();
        int total = serviceTypeMapper.getAllServTypeCount();
        List<ServiceType> rs = serviceTypeMapper.getPagedServType(offset, limit);

        result.setResults(rs);
        result.setTotal(total);
        result.setPageSize(limit);
        result.setPageNo( offset / limit + 1);

        return result;
    }

    public ServiceType findServiceTypeById(int id) {
        ServiceType serviceType = serviceTypeMapper.findServTypeById(id);

        if (serviceType == null) {
            logger.warn("Can not find any ServiceType by ID: {}", id);
        }

        return serviceType;
    }

    public List<ServiceType> findServiceTypeByName(String serviceTypeName) {
        List<ServiceType> serviceTypes = serviceTypeMapper.findServTypeByName(serviceTypeName);

        if (serviceTypes.size() == 0) {
            logger.warn("Can not find any ServiceType by Name: {}", serviceTypeName);
        }

        return serviceTypes;
    }

    public void saveServiceType(ServiceType newServiceType) throws DpsBizException {
        List<ServiceType> existingServTypes = findServiceTypeByName(newServiceType.getServTypeName());

        if (existingServTypes.size() > 0) {
            logger.warn("The same ServiceType: {} already exists.", newServiceType);
            throw new DpsBizException("501", "Same ServiceType is already existing.");
        }

        boolean retVal = serviceTypeMapper.saveServType(newServiceType);

        if (retVal) {
            logger.debug("New ServiceType[{}] is saved successfully.");
        } else {
            throw new DpsBizException("502", "ServiceType is not saved.");
        }
    }

    public void updateServiceType(ServiceType serviceType) throws DpsBizException {
        ServiceType existingServiceType = findServiceTypeById(serviceType.getServTypeId());
        if (existingServiceType == null) {
            logger.warn("Failed to update ServiceType to {} due to not exist.", serviceType);
            throw new DpsBizException("503", "ServiceType is null.");
        }

        String newServTypeName = serviceType.getServTypeName();

        List<ServiceType> existingServiceTypes = findServiceTypeByName(newServTypeName);
        if (existingServiceTypes.size() > 0) {
            logger.warn("Failed to update ServiceType to {} due to duplicate name.", serviceType);
            throw new DpsBizException("504", "The ServiceType new name is duplicated with existing ones.");
        }

        boolean retVal = serviceTypeMapper.updateServType(serviceType);

        if (retVal) {
            logger.debug("ServiceType[{}] is updated successfully.");
        } else {
            throw new DpsBizException("505", "ServiceType is not updated.");
        }
    }

    public void deleteServiceType(int serviceTypeId) throws DpsBizException {
        ServiceType existingServiceType = findServiceTypeById(serviceTypeId);

        boolean retVal = serviceTypeMapper.deleteServType(serviceTypeId);

        if (retVal) {
            logger.debug("ServiceType [id={}] is deleted successfully.", serviceTypeId);
        } else {
            throw new DpsBizException("507", "ServiceType is not deleted.");
        }
    }

}
