package com.dps.user.vo;

import com.dps.common.enums.StatusEnum;
import com.dps.common.enums.UserTypeEnum;

/**
 * VO for user.
 */
public class UserVo {
    private int id;

    private String name;

    private UserTypeEnum type;

    private StatusEnum status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserTypeEnum getType() {
        return type;
    }

    public void setType(UserTypeEnum type) {
        this.type = type;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }
}
