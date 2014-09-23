package com.dps.common.enums;

import com.dps.common.enums.jsonserializer.GenericEnumSerializer;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(using=GenericEnumSerializer.class)
public enum StatusEnum implements GenericEnum {
    //user status
    CREATE          (100, "CREATE"),
    APPROVING       (101, "approving"),
    ACTIVE          (102, "active"),
    INACTIVE        (103, "inactive"),
    //SP status
    CREATE_SP           (200, "创建"),
    WAIT_APPROVE_SP     (201, "待审核"),
    APPROVING_SP        (202, "审核中"),
    ACTIVE_SP           (203, "活跃"),
    REAPPROVING_SP      (204, "重新审核"),
    FROZEN_SP           (205, "冻结"),
    INACTIVE_SP         (206, "不活跃"),
    //product status
    CREATE_PRODUCT          (300, "创建"),
    WAIT_AUDITING_PRODUCT   (301, "待审核"),
    AUDITING_PRODUCT        (302, "审核中"),
    ONLINE_PRODUCT          (303, "已上线"),
    OFFLINE_PRODUCT         (304, "已下线"),
    FREEZE_PRODUCT          (305, "冻结"),
    //common status
    COMMON_ACTITVE          (1, "active"),
    COMMON_INACTIVE         (0, "inactive");

    private StatusEnum(int id, String name) {
        this.id = id;
        this.name = name;
    }

    private int id;
    private String name;

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

    public static StatusEnum getEnumByID(int id) {
        for (StatusEnum ele : StatusEnum.values()) {
            if (ele.getId() == id)
                return ele;
        }
        return null;
    }
}
