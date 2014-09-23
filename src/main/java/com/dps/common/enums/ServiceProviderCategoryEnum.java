package com.dps.common.enums;

import com.dps.common.enums.jsonserializer.GenericEnumSerializer;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * Service Provider cate
 */
@JsonSerialize(using=GenericEnumSerializer.class)
public enum ServiceProviderCategoryEnum implements GenericEnum {
    COURT_SERV_PROVIDER     (1, "CourtServiceProvider"),
    CLUB_SERV_PROVIDER      (2, "ClubServiceProvider"),
    COACH_SERV_PROVIDER     (3, "CoachServiceProvider");

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

    ServiceProviderCategoryEnum(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public static ServiceProviderCategoryEnum getEnumById(int id) {
        ServiceProviderCategoryEnum[] enums = values();
        for (ServiceProviderCategoryEnum e : enums) {
            if (e.getId() == id) {
                return e;
            }
        }
        return null;
    }
}
