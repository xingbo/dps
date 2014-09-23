package com.dps.cos.pay.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.dps.cos.pay.vo.PayChannel;


@Repository
public interface PayChannelDAO {
	@Select("select count(1) from  PayChannel ")
	public int getPayChannelTotal();

	@Select("select * from PayChannel limit #{offset}, #{limit}")
	public List<PayChannel> getPagedPayChannels(@Param("offset") int offset, @Param("limit") int limit);

	@Select("select * from PayChannel ")
	public List<PayChannel> findPayChannel();

	@Select("select * from PayChannel where id = #{id}")
	public PayChannel getPayChannel(long id);

	@Delete("delete from  PayChannel where id = #{id}")
	public void deletePayChannel(long id);

	@Insert({"insert into PayChannel(id,name,status,result,type,description,createDate,updateDate) values (#{id},#{name},#{status},#{result},#{type},#{description},#{createDate},#{updateDate})"})
	@Options(keyProperty = "id", useGeneratedKeys = true)
	public void savePayChannel(PayChannel paychannel);

	@Update({"update PayChannel set id=#{id},name=#{name},status=#{status},result=#{result},type=#{type},description=#{description},createDate=#{createDate},updateDate=#{updateDate} where id = #{id}"})
	public void updatePayChannel(PayChannel paychannel);


}
