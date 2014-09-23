package com.dps.sp.vo;

import com.dps.common.enums.ServiceProviderCategoryEnum;
import com.dps.common.enums.StatusEnum;
import com.dps.user.vo.UserVo;

import java.util.Date;

/**
 * VO for Service Provider.
 */
public class ServiceProviderVo {

    private int spId;

    private String spName;

    private ServiceProviderCategoryEnum spCategory;

    private String spCity;

    private String spDistrict;

    private String spAddress;

    private String spPhone;

    private String spServArea;

    private String spAdvtImage;

    private String spCertImage;

    private String spDescription;

    private StatusEnum spStatus;

    private Date createDate;

    private Date updateDate;

    private UserVo user;


    public int getSpId() {
        return spId;
    }

    public void setSpId(int spId) {
        this.spId = spId;
    }

    public String getSpName() {
        return spName;
    }

    public void setSpName(String spName) {
        this.spName = spName;
    }

    public ServiceProviderCategoryEnum getSpCategory() {
        return spCategory;
    }

    public void setSpCategory(ServiceProviderCategoryEnum spCategory) {
        this.spCategory = spCategory;
    }

    public String getSpCity() {
        return spCity;
    }

    public void setSpCity(String spCity) {
        this.spCity = spCity;
    }

    public String getSpDistrict() {
        return spDistrict;
    }

    public void setSpDistrict(String spDistrict) {
        this.spDistrict = spDistrict;
    }

    public String getSpAddress() {
        return spAddress;
    }

    public void setSpAddress(String spAddress) {
        this.spAddress = spAddress;
    }

    public String getSpPhone() {
        return spPhone;
    }

    public void setSpPhone(String spPhone) {
        this.spPhone = spPhone;
    }

    public String getSpServArea() {
        return spServArea;
    }

    public void setSpServArea(String spServArea) {
        this.spServArea = spServArea;
    }

    public String getSpAdvtImage() {
        return spAdvtImage;
    }

    public void setSpAdvtImage(String spAdvtImage) {
        this.spAdvtImage = spAdvtImage;
    }

    public String getSpCertImage() {
        return spCertImage;
    }

    public void setSpCertImage(String spCertImage) {
        this.spCertImage = spCertImage;
    }

    public String getSpDescription() {
        return spDescription;
    }

    public void setSpDescription(String spDescription) {
        this.spDescription = spDescription;
    }

    public StatusEnum getSpStatus() {
        return spStatus;
    }

    public void setSpStatus(StatusEnum spStatus) {
        this.spStatus = spStatus;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public UserVo getUser() {
        return user;
    }

    public void setUser(UserVo user) {
        this.user = user;
    }

}
