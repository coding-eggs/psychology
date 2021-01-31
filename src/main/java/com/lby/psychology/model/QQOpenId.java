package com.lby.psychology.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class QQOpenId {

    @ApiModelProperty(value = "客户端id(app id)")
    private String client_id;

    @ApiModelProperty(value = "openId")
    private String openid;

}
