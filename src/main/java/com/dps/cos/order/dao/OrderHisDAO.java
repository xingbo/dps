package com.dps.cos.order.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.dps.cos.order.vo.OrderHis;


@Repository
public interface OrderHisDAO {
	@Select("select count(1) from  OrderHis ")
	public int getOrderHisTotal();

	@Select("select * from OrderHis limit #{offset}, #{limit}")
	public List<OrderHis> getPagedOrderHiss(@Param("offset") int offset, @Param("limit") int limit);

	@Select("select * from OrderHis ")
	public List<OrderHis> findOrderHis();

	@Select("select * from OrderHis where id = #{id}")
	public OrderHis getOrderHis(long id);

	@Delete("delete from  OrderHis where id = #{id}")
	public void deleteOrderHis(long id);

	@Insert({"insert into OrderHis(id,title,code,type,userId,serviceId,status,description,createDate,updateDate,toHisDate) values (#{id},#{title},#{code},#{type},#{userId},#{serviceId},#{status},#{description},#{createDate},#{updateDate},#{toHisDate})"})
	@Options(keyProperty = "id", useGeneratedKeys = true)
	public void saveOrderHis(OrderHis orderhis);

	@Update({"update OrderHis set id=#{id},title=#{title},fee=#{fee},code=#{code},type=#{type},userId=#{userId},serviceId=#{serviceId},status=#{status},description=#{description},createDate=#{createDate},updateDate=#{updateDate},toHisDate=#{toHisDate} where id = #{id}"})
	public void updateOrderHis(OrderHis orderhis);

}
