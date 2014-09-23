package com.dps.sp.dao;

import com.dps.sp.domain.ServiceType;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * DAO for Service Type.
 */
@Repository
public interface ServiceTypeMapper {

    int getLatestId();

    int getAllServTypeCount();

    List<ServiceType> getAllServType();

    List<ServiceType> getPagedServType(@Param("offset")int offset, @Param("limit")int limit);

    ServiceType findServTypeById(int id);

    List<ServiceType> findServTypeByName(String servTypeName);

    boolean saveServType(ServiceType serviceType);

    boolean updateServType(ServiceType serviceType);

    boolean deleteServType(int id);

}
