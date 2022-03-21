package com.lby.psychology.model.enums;

import lombok.Getter;

@Getter
public enum EnumRedisPre {
    EMAIL_REG_CHECK("ERC:"),
    EMAIL_FORGET_CHECK("EFC:");


    EnumRedisPre(String code) {
        this.code = code;
    }

    private final String code;

}
