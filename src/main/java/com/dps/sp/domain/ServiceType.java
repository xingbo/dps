package com.dps.sp.domain;

/**
 * ServiceType
 */
public class ServiceType {

    private Integer servTypeId;

    private String servTypeName;

    private String servTypeDesc;


    public Integer getServTypeId() {
        return servTypeId;
    }

    public void setServTypeId(Integer servTypeId) {
        this.servTypeId = servTypeId;
    }

    public String getServTypeName() {
        return servTypeName;
    }

    public void setServTypeName(String servTypeName) {
        this.servTypeName = servTypeName;
    }

    public String getServTypeDesc() {
        return servTypeDesc;
    }

    public void setServTypeDesc(String servTypeDesc) {
        this.servTypeDesc = servTypeDesc;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("servTypeId:").append(servTypeId).append(",")
            .append("servTypeName:").append(servTypeName).append(",")
            .append("servTypeDesc:").append(servTypeDesc);

        return sb.toString();
    }
}
