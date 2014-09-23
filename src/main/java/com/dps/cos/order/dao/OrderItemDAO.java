package com.dps.cos.order.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.dps.cos.order.vo.OrderItem;


@Repository
public interface OrderItemDAO {
	@Select("select count(1) from  OrderItem ")
	public int getOrderItemTotal();

	@Select("select * from OrderItem limit #{offset}, #{limit}")
	public List<OrderItem> getPagedOrderItems(@Param("offset") int offset, @Param("limit") int limit);

	@Select("select * from OrderItem ")
	public List<OrderItem> findOrderItem();

	@Select("select * from OrderItem where id = #{id}")
	public OrderItem getOrderItem(long id);

	@Delete("delete from  OrderItem where id = #{id}")
	public void deleteOrderItem(long id);

	@Insert({"insert into OrderItem(id,serviceId,amount,price,title,orderId,status,createDate,updateDate) values (#{id},#{serviceId},#{amount},#{price},#{title},#{orderId},#{status},#{createDate},#{updateDate})"})
	@Options(keyProperty = "id", useGeneratedKeys = true)
	public void saveOrderItem(OrderItem orderItem);

	@Update({"update OrderItem set id=#{id},serviceId=#{serviceId},price=#{price},title=#{title},amount=#{amount},orderId=#{orderId},status=#{status},createDate=#{createDate},updateDate=#{updateDate} where id = #{id}"})
	public void updateOrderItem(OrderItem orderItem);

	@Select("select * from OrderItem where orderId=#{orderId}")
	public List<OrderItem> findOrderItemByOrder(long orderId);

	@Update({"update OrderItem set status=#{status} updateDate=now() where id = #{id}"})
	public void updateOrderItemStatus(@Param("id") long id, @Param("status") int status);


}
