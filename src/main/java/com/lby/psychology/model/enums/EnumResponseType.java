package com.lby.psychology.model.enums;

import lombok.Getter;

@Getter
public enum EnumResponseType {

    SUCCESS(200,"成功"),
    NON_AUTH_ENTRY_POINT(403,"权限不足"),
    BAD_CERTIFICATE(20000,"用户名或密码不正确"),
    USER_UN_LOGIN(10000,"未登录"),
    USER_SESSION_EXPIRED(10001,"session 已过期"),
    USER_SESSION_EXPIRED_CON(10002,"并发导致 session 过期"),
    BEAN_VALIDATION_EXCEPTION(20001,"参数校验异常"),
    METHOD_ARGUMENT_NOT_VALID_EXCEPTION(20002,"实体类参数校验异常"),
    EMAIL_IS_EXIST(20003,"该用户已存在"),
    VALID_CODE_NOT_SAME(20004,"验证码不正确"),
    UPLOAD_FIGURE_URL(2005,"上传头像失败 minio server"),
    ERROR(5000,"服务端异常"),
    OLD_PASSWORD_ERROR(20006,"原密码错误"),
    TWO_PASSWORD_NOT_SAME(20007,"新密码不能与原密码相同"),
    OLD_NEW_PASSWORD_NOT_SAME(20008,"新密码和旧密码不能相同"),
    EMAIL_NOT_EXIST(20009,"该用户不存在");

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
