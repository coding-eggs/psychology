package com.lby.psychology.config;

import com.lby.psychology.model.enums.EnumResponseType;
import lombok.Getter;

/**
* 自定义异常处理
* @author myk
*/
@Getter
public class PsycException extends RuntimeException{

    private final Integer code;

    private final String msg;


    public PsycException(Integer code,String msg,Throwable throwable){
        super(msg,throwable);
        this.code = code;
        this.msg = msg;
    }

    public PsycException(EnumResponseType responseType){
        super(responseType.getMsg());
        this.code = responseType.getCode();
        this.msg = responseType.getMsg();
    }

}
