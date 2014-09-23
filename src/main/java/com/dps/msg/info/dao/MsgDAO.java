package com.dps.msg.info.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.dps.msg.info.vo.Msg;


@Repository
public interface MsgDAO {
	@Select("select count(1) from  Msg ")
	public int getMsgTotal();

	@Select("select * from Msg limit #{offset}, #{limit}")
	public List<Msg> getPagedMsgs(@Param("offset") int offset, @Param("limit") int limit);

	@Select("select * from Msg ")
	public List<Msg> findMsg();

	@Select("select * from Msg where id = #{id}")
	public Msg getMsg(long id);

	@Delete("delete from  Msg where id = #{id}")
	public void deleteMsg(long id);

	@Insert({"insert into Msg(id,title,type,level,content,status,createDate,updateDate) values (#{id},#{title},#{type},#{level},#{content},#{status},#{createDate},#{updateDate})"})
	@Options(keyProperty = "id", useGeneratedKeys = true)
	public void saveMsg(Msg msg);

	@Update({"update Msg set id=#{id},title=#{title},type=#{type},level=#{level},content=#{content},status=#{status},createDate=#{createDate},updateDate=#{updateDate} where id = #{id}"})
	public void updateMsg(Msg msg);


}
