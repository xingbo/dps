package com.dps.css.product.court.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.dps.css.product.court.vo.Court;


@Repository
public interface CourtDAO {
	@Select("select count(1) from  Court ")
	public int getCourtTotal();

	@Select("select * from Court limit #{offset}, #{limit}")
	public List<Court> getPagedCourts(@Param("offset") int offset, @Param("limit") int limit);

	@Select("select * from Court ")
	public List<Court> findCourt();

	@Select("select * from Court where id = #{id}")
	public Court getCourt(long id);

	@Delete("delete from  Court where id = #{id}")
	public void deleteCourt(long id);

	@Insert({"insert into Court(id,code,name,type,level,num,area,linker,address,phone,description) values (#{id},#{code},#{name},#{type},#{level},#{num},#{area},#{linker},#{address},#{phone},#{description})"})
	@Options(keyProperty = "id", useGeneratedKeys = true)
	public void saveCourt(Court court);

	@Update({"update Court set id=#{id},code=#{code},name=#{name},type=#{type},level=#{level},num=#{num},area=#{area},linker=#{linker},address=#{address},phone=#{phone},description=#{description} where id = #{id}"})
	public void updateCourt(Court court);


}
