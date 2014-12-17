package com.dps.sp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.dps.sp.vo.Coach;



@Repository
public interface CoachDAO {
	@Select("select count(1) from  coach_user ")
	public int getCoachTotal();

	@Select("select * from coach_user limit #{offset}, #{limit}")
	public List<Coach> getPagedCoachs(@Param("offset") int offset, @Param("limit") int limit);

	@Select("select * from coach_user ")
	public List<Coach> findCoach();

	@Select("select * from coach_user where userid = #{id}")
	public Coach getCoach(long id);

	@Delete("delete from  coach_user where id = #{id}")
	public void deleteCoach(long id);

	@Insert({"insert into coach_user(id,name,type,level,phone,age,birthday,address) values (#{id},#{name},#{type},#{level},#{phone},#{age},#{birthday},#{address})"})
	@Options(keyProperty = "id", useGeneratedKeys = true)
	public void saveCoach(Coach coach);

	@Update({"update coach_user set id=#{id},name=#{name},type=#{type},level=#{level},phone=#{phone},age=#{age},birthday=#{birthday},address=#{address} where id = #{id}"})
	public void updateCoach(Coach coach);


}
