package com.lby.psychology.model.enums;

import lombok.Getter;

@Getter
public enum EnumUserStatus {

    USER_UN_ENABLE((short)0,"未激活"),
    USER_ENABLE((short)1,"已激活");

    private Short id;

    private String name;

    EnumUserStatus(Short id, String name) {
        this.id = id;
        this.name = name;
    }
    public static EnumUserStatus getEnumById(int id) {
        for(EnumUserStatus enumEnable : EnumUserStatus.values()) {
            if (enumEnable.getId()==id)
                return enumEnable;
        }
        return null;
    }
}
