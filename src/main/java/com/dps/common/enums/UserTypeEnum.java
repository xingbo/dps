package com.dps.common.enums;

import com.dps.common.enums.jsonserializer.GenericEnumSerializer;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * User type definition.
 */
@JsonSerialize(using=GenericEnumSerializer.class)
public enum UserTypeEnum implements GenericEnum {
    CONSUMER        (1, "consumer"),
    COACH           (2, "coach"),
    ClUBANDCOURT    (3, "clubandcourt");

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

    private UserTypeEnum(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
