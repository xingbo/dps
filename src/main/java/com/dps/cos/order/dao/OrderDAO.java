package com.dps.cos.order.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.dps.cos.order.vo.Order;


@Repository
public interface OrderDAO {
	@Select("select count(1) from  Order ")
	public int getOrderTotal();

	@Select("select * from Order limit #{offset}, #{limit}")
	public List<Order> getPagedOrders(@Param("offset") int offset, @Param("limit") int limit);

	@Select("select * from Order ")
	public List<Order> findOrder();

	@Select("select * from Order where id = #{id}")
	public Order getOrder(long id);

	@Delete("delete from  Order where id = #{id}")
	public void deleteOrder(long id);

	@Insert({"insert into Order(id,title,type,code,userId,fee,serviceId,status,description,createDate,updateDate) values (#{id},#{title},#{type},#{code},#{userId},#{fee},#{serviceId},#{status},#{description},#{createDate},#{updateDate})"})
	@Options(keyProperty = "id", useGeneratedKeys = true)
	public void saveOrder(Order order);

	@Update({"update Order set id=#{id},title=#{title},code=#{code},type=#{type},userId=#{userId},serviceId=#{serviceId},status=#{status},fee=#{fee},description=#{description},createDate=#{createDate},updateDate=#{updateDate} where id = #{id}"})
	public void updateOrder(Order order);

	@Update({"update Order set status=#{status},updateDate=now() where id = #{id}"})
	public void updateOrderStatus(@Param("id") long id, @Param("status") int status);
}
