package com.lby.psychology.model.enums;

import lombok.Getter;

@Getter
public enum EnumPermissionType {
    INTERFACE(10,"接口"),
    PAGE(20,"页面");

    private final Integer id;
    private final String name;

    EnumPermissionType(Integer id, String name) {
        this.id=id;
        this.name = name;
    }
}
