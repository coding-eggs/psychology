package com.lby.psychology.model;

import com.lby.psychology.model.enums.EnumResponseType;
import lombok.Data;
import lombok.Getter;

@Getter
public class ResponseData<T> {

    /**
     * 响应代码
     */
    private Integer code;

    /**
     * 响应消息
     */
    private String msg;

    /**
     * 响应结果
     */
    private T data;

    public ResponseData(){

    }

    public ResponseData(Integer code, T data) {
        this.code = code;
        this.data = data;
        this.msg = EnumResponseType.getMsgByCode(code);
    }

    public ResponseData(T data) {
        this.code = EnumResponseType.SUCCESS.getCode();
        this.msg = EnumResponseType.SUCCESS.getMsg();
        this.data = data;
    }

    public void setCode(Integer code) {
        this.code = code;
        this.msg = EnumResponseType.getMsgByCode(code);
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(T data) {
        this.data = data;
    }
}
