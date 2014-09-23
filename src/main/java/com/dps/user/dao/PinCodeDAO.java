package com.dps.user.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.dps.user.domain.PinCode;
import com.dps.user.domain.User;

@Repository
public interface PinCodeDAO {
    @Select("select count(1) from  PinCode ")
    public int getPinCodeTotal();

    @Insert({"insert into PinCode(pinCode,cellphone,status) values (#{pinCode},#{cellphone},#{status})"})
    @Options(keyProperty = "id", useGeneratedKeys = true)
    public void savePinCode(PinCode PinCode);

    @Select("select * from PinCode where pinCode = #{pinCode} and status = 0")
    public User getActivePinCode(String pinCode, String PhoneNum);

    @Update("update PinCode set status=#{1} from  PinCode where pinCode = #{pinCode} and cellphone=#{cellphone} and status = 0")
    public void updatePinCodeToInactive(String pinCode, String cellphone);
}
