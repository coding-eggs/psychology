package com.lby.psychology.model.enums;

import lombok.Getter;

@Getter
public enum EnumResponseType {

    SUCCESS(200,"成功"),
    NON_AUTH_ENTRY_POINT(403,"权限不足"),
    USER_SESSION_EXPIRED(10001,"session 已过期"),
    BEAN_VALIDATION_EXCEPTION(20001,"参数校验异常"),
    METHOD_ARGUMENT_NOT_VALID_EXCEPTION(20002,"实体类参数校验异常"),
    EMAIL_IS_EXIST(20003,"该用户已存在"),
    VALID_CODE_NOT_SAME(20004,"验证码不正确"),;

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
