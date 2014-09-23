package com.dps.cos.pay.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.dps.cos.pay.vo.PayInfo;

@Repository
public interface PayInfoDAO {
	@Select("select count(1) from  PayInfo ")
	public int getPayInfoTotal();

	@Select("select * from PayInfo limit #{offset}, #{limit}")
	public List<PayInfo> getPagedPayInfos(@Param("offset") int offset, @Param("limit") int limit);

	@Select("select * from PayInfo ")
	public List<PayInfo> findPayInfo();

	@Select("select * from PayInfo where id = #{id}")
	public PayInfo getPayInfo(long id);

	@Delete("delete from  PayInfo where id = #{id}")
	public void deletePayInfo(long id);

	@Insert({ "insert into PayInfo(id,title,type,result,userId,orderId,fee,status,description,createDate,updateDate) values (#{id},#{title},#{type},#{result},#{userId},#{orderId},#{fee},#{status},#{description},#{createDate},#{updateDate})" })
	@Options(keyProperty = "id", useGeneratedKeys = true)
	public void savePayInfo(PayInfo payinfo);

	@Update({ "update PayInfo set id=#{id},title=#{title},type=#{type},userId=#{userId},orderId=#{orderId},fee=#{fee},result=#{result},status=#{status},description=#{description},createDate=#{createDate},updateDate=#{updateDate} where id = #{id}" })
	public void updatePayInfo(PayInfo payinfo);

	@Select("select * from PayInfo where orderId = #{orderId} and status in (1,2,3)")
	public List<PayInfo> findAvailablePayinfoByOrder(long orderId);

	@Update({ "update PayInfo set status=#{status} ,updateDate=now() where id = #{id}" })
	public void updatePayInfoStatus(@Param("id") long id, @Param("status") int status);

	@Update({ "update PayInfo set status=#{status} ,result=#{result} ,updateDate=now() where id = #{id}" })
	public void updatePayInfoStatusAndResult(@Param("id") long id, @Param("status") int status, @Param("result") int result);

}
