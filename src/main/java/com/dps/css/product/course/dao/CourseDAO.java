package com.dps.css.product.course.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.dps.css.product.course.vo.Course;


@Repository
public interface CourseDAO {
	@Select("select count(1) from  Course ")
	public int getCourseTotal();

	@Select("select * from Course limit #{offset}, #{limit}")
	public List<Course> getPagedCourses(@Param("offset") int offset, @Param("limit") int limit);

	@Select("select * from Course ")
	public List<Course> findCourse();

	@Select("select * from Course where id = #{id}")
	public Course getCourse(long id);

	@Delete("delete from  Course where id = #{id}")
	public void deleteCourse(long id);

	@Insert({"insert into Course(id,code,name,type,level,courseTime,num,area,linker,address,phone,description) values (#{id},#{code},#{name},#{type},#{level},#{courseTime},#{num},#{area},#{linker},#{address},#{phone},#{description})"})
	@Options(keyProperty = "id", useGeneratedKeys = true)
	public void saveCourse(Course course);

	@Update({"update Course set id=#{id},code=#{code},name=#{name},type=#{type},level=#{level},courseTime=#{courseTime},num=#{num},area=#{area},linker=#{linker},address=#{address},phone=#{phone},description=#{description} where id = #{id}"})
	public void updateCourse(Course course);


}
