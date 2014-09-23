package com.dps.sp.dao;

import java.util.Date;
import java.util.List;

import com.dps.common.enums.StatusEnum;
import com.dps.sp.domain.ServiceType;
import com.dps.sp.vo.ServiceProviderDetailVo;
import com.dps.sp.vo.ServiceProviderVo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.dps.sp.domain.ServiceProvider;

@Repository
public interface ServiceProviderMapper {

    int getLatestId();

	int getTotalSPNum();

	List<ServiceProviderVo> getPagedSP(@Param("offset")int offset, @Param("limit")int limit);

    ServiceProviderDetailVo findSPById(int id);

    List<ServiceProvider> findSPByName(@Param("name")String name);

    List<ServiceProvider> findSPByCategory(@Param("category")int category);

	List<ServiceProvider> findSPByStatus(@Param("status")int status);

    List<ServiceProvider> findSPByUserId(@Param("userId")int userId);

	List<ServiceProvider> findSPByStatusAndUserId(@Param("status")int status, @Param("userId")int userId);

    boolean saveServProvider(ServiceProvider serviceProvider);

    boolean updateServProvider(ServiceProvider serviceProvider);

    boolean deleteServProvider(int id);

    boolean updateServProviderStatus(@Param("status")int newStatus);

    boolean saveSP2UserRelation(@Param("userid")int userid, @Param("spid")int spid, @Param("isowner")int isowner,
                                @Param("status")int status, @Param("createdate")Date createdate, @Param("updatedate")Date updatedate);

    boolean saveSP2STRelation(@Param("spid")int spid, @Param("stid")int stid, @Param("status")int status,
                              @Param("createdate")Date createdate, @Param("updatedate")Date updatedate);

    boolean updateServProviderStatus(@Param("spid")int spid, @Param("nextStatus")StatusEnum nextStatus);

    boolean addServProviderForServType(ServiceProvider serviceProvider, ServiceType serviceType);

}

