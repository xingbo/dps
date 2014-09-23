package com.dps.user.dao;

import java.util.List;

import com.dps.user.vo.UserListVo;
import com.dps.user.vo.UserVo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.dps.user.domain.User;

@Repository
public interface UserMapper {

	public int getAllUserCount();

	public List<UserListVo> getPagedUsers(@Param("offset") int offset, @Param("limit") int limit);

    public List<UserVo> getCUsers();

	public UserVo findUserById(Integer id);

//	@Select("select * from User ")
//	public List<? extends User> findAllUser();
//
//    @Select("select ID, NAME, STATUS from USER where type = #{typeId}")
//    public List<User> getUserBrif(int typeId);
//

//
//    @Select("select * from User where name = #{name}")
//    public User findUserByName(String name);
//
////	@Select("select * from User where phone = #{phone} and type=#{userType} ")
////	public User getUserByPhone(String phone, int userType);
////
////	@Select("select * from User where phone = #{phoneOrName} or name = #{phoneOrName}")
////	public User getUserByPhoneOrName(String phoneOrName);
//
//	@Insert({"insert into User(id,login,name,password,type,phone,birthday,city, areas, status,createDate,updateDate) values (#{id},#{login},#{name},#{type},#{phone},#{birthday},#{city},#{areas},#{status},#{createDate},#{updateDate})"})
//	@Options(keyProperty = "id", useGeneratedKeys = true)
//	public void saveUser(User user);
//
//	@Update({"update User set id=#{id},login=#{login},name=#{name},password=#{password},type=#{type},phone=#{phone},birthday=#{birthday},city=#{city},areas=#{areas},status=#{status},createDate=#{createDate},updateDate=#{updateDate} where id = #{id}"})
//	public void updateUser(User user);
//
//    @Delete("delete from  User where id = #{id}")
//    public void deleteUser(long id);
//
//	@Update("update User set status=#{3} from  User where id = #{id}")
//	public void updateUserToInactive(long id);
}
