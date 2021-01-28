package com.lby.psychology.model.enums;

import lombok.Getter;

@Getter
public enum EnumResponseType {

    SUCCESS(200,"成功"),
    NON_AUTH_ENTRY_POINT(403,"权限不足"),
    USER_SESSION_EXPIRED(10001,"session 已过期");

    EnumResponseType(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private final Integer code;

    private final String msg;

    public static String getMsgByCode(Integer code){
        for(EnumResponseType e: EnumResponseType.values()){
            if(e.getCode().equals(code)){
                return e.getMsg();
            }
        }
        return null;
    }
}
