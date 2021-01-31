package com.lby.psychology.model;

import com.lby.psychology.model.enums.EnumResponseType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;

@Getter
public class ResponseData<T> {

    /**
     * 响应代码
     */
    @ApiModelProperty(value = "状态码")
    private Integer code;

    /**
     * 响应消息
     */
    @ApiModelProperty(value = "响应提示")
    private String msg;

    /**
     * 响应结果
     */
    @ApiModelProperty(value = "响应数据")
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
