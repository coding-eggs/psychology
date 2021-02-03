package com.lby.psychology.model.enums;

import lombok.Getter;

@Getter
public enum EnumRedisPre {
    EMAIL_CHECK("EC");


    EnumRedisPre(String code) {
        this.code = code;
    }

    private final String code;

}
