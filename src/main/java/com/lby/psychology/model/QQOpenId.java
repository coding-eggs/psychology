package com.lby.psychology.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "com.lby.psychology.model.QQOpenId")
public class QQOpenId {

    @ApiModelProperty(value = "客户端id(app id)")
    private String client_id;

    @ApiModelProperty(value = "openId")
    private String openid;

}
