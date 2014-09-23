package com.dps.css.service.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.dps.css.service.vo.DPService;


@Repository
public interface DPServiceDAO {
	@Select("select count(1) from  DPService ")
	public int getDPServiceTotal();

	@Select("select * from DPService limit #{offset}, #{limit}")
	public List<DPService> getPagedDPServices(@Param("offset") int offset, @Param("limit") int limit);

	@Select("select * from DPService ")
	public List<DPService> findDPService();

	@Select("select * from DPService where id = #{id}")
	public DPService getDPService(long id);

	@Delete("delete from  DPService where id = #{id}")
	public void deleteDPService(long id);

	@Insert({"insert into DPService(id,title,type,status,description,createDate,updateDate) values (#{id},#{title},#{type},#{status},#{description},#{createDate},#{updateDate})"})
	@Options(keyProperty = "id", useGeneratedKeys = true)
	public void saveDPService(DPService dpservice);

	@Update({"update DPService set id=#{id},title=#{title},type=#{type},status=#{status},description=#{description},createDate=#{createDate},updateDate=#{updateDate} where id = #{id}"})
	public void updateDPService(DPService dpservice);


}
