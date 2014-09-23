package com.dps.cos.order.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.dps.cos.order.vo.ServicePinCode;

@Repository
public interface ServicePinCodeDAO {
	@Select("select count(1) from  ServicePinCode ")
	public int getServicePinCodeTotal();

	@Select("select * from ServicePinCode limit #{offset}, #{limit}")
	public List<ServicePinCode> getPagedServicePinCodes(@Param("offset") int offset, @Param("limit") int limit);

	@Select("select * from ServicePinCode ")
	public List<ServicePinCode> findServicePinCode();

	@Select("select * from ServicePinCode where id = #{id}")
	public ServicePinCode getServicePinCode(long id);

	@Delete("delete from  ServicePinCode where id = #{id}")
	public void deleteServicePinCode(long id);

	@Insert({ "insert into ServicePinCode(id,code,orderId,orderItemId,type,serviceTime,createTime,updateTime,expiredTime,userId,phone,status) values (#{id},#{code},#{orderId},#{orderItemId},#{type},#{serviceTime},#{createTime},#{updateTime},#{expiredTime},#{userId},#{phone},#{status})" })
	@Options(keyProperty = "id", useGeneratedKeys = true)
	public void saveServicePinCode(ServicePinCode servicepincode);

	@Update({ "update ServicePinCode set id=#{id},code=#{code},orderId=#{orderId},orderItemId=#{orderItemId},type=#{type},serviceTime=#{serviceTime},createTime=#{createTime},updateTime=#{updateTime},expiredTime=#{expiredTime},userId=#{userId},phone=#{phone},status=#{status} where id = #{id}" })
	public void updateServicePinCode(ServicePinCode servicepincode);

	@Update({ "update ServicePinCode set status=#{status},updateTime=now() where orderId=#{orderId}" })
	public void updateStatusByOrderId(@Param("orderId") long orderId, @Param("status") int status);

	@Select("select count(1) from  ServicePinCode where orderId=#{orderId} and status=#{status}")
	public int getCountByOrderIdAndStatus(@Param("orderId") long orderId, @Param("status") int status);

	@Select("select * from ServicePinCode where orderId=#{orderId} and code in (#{codes})")
	public List<ServicePinCode> findServicePinCodesByOrderIdAndCodes(@Param("orderId") long orderId, @Param("codes") String[] codes);

	@Update({ "update ServicePinCode set status=#{status},updateTime=now() where id=#{id}" })
	public void updateStatusById(@Param("id") long id, @Param("status") int status);

}
