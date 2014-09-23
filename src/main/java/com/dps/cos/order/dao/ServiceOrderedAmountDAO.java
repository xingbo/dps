package com.dps.cos.order.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.dps.cos.order.vo.ServiceOrderedAmount;


@Repository
public interface ServiceOrderedAmountDAO {
	@Select("select count(1) from  ServiceOrderedAmount ")
	public int getServiceOrderedAmountTotal();

	@Select("select * from ServiceOrderedAmount limit #{offset}, #{limit}")
	public List<ServiceOrderedAmount> getPagedServiceOrderedAmounts(@Param("offset") int offset, @Param("limit") int limit);

	@Select("select * from ServiceOrderedAmount ")
	public List<ServiceOrderedAmount> findServiceOrderedAmount();

	@Select("select * from ServiceOrderedAmount where id = #{id}")
	public ServiceOrderedAmount getServiceOrderedAmount(long id);

	@Delete("delete from  ServiceOrderedAmount where id = #{id}")
	public void deleteServiceOrderedAmount(long id);

	@Insert({"insert into ServiceOrderedAmount(id,serviceId,amount,serviceDate) values (#{id},#{serviceId},#{amount},#{serviceDate})"})
	@Options(keyProperty = "id", useGeneratedKeys = true)
	public void saveServiceOrderedAmount(ServiceOrderedAmount orderedAmount);

	@Update({"update ServiceOrderedAmount set id=#{id},serviceId=#{serviceId},amount=#{amount},serviceDate=#{serviceDate} where id = #{id}"})
	public void updateServiceOrderedAmount(ServiceOrderedAmount orderedAmount);

	@Select("select * from ServiceOrderedAmount where serviceId=#{serviceId} and serviceDate=#{serviceDate} ")
	public ServiceOrderedAmount getServiceOrderedAmountByServiceIdAndServiceDate(@Param("serviceId") long serviceId, @Param("serviceDate") Date serviceDate);

	@Update({"update ServiceOrderedAmount set amount=#{amount} where id = #{id}"})
	public void updateServiceOrderedAmountInr(@Param("id") long id, @Param("amount") int amount);
	
	@Update({"update ServiceOrderedAmount set amount=amount - #{amount} where serviceId=#{serviceId} and serviceDate=#{serviceDate}"})
	public void updateServiceOrderedAmountdecr(@Param("serviceId") long serviceId, @Param("serviceDate") Date serviceDate, @Param("amount") int amount);
}
