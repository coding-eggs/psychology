package com.lby.psychology.model.enums;

import lombok.Getter;

@Getter
public enum EnumAuthType {

    EMAIL(10,"邮箱"),
    QQ(20,"qq");

    private final Integer id;
    private final String name;

    EnumAuthType(Integer id, String name) {
        this.id=id;
        this.name = name;
    }

}
