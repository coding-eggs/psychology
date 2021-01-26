package com.lby.psychology.model.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Data;

/**
 * psyc_permission
 * @author 
 */
@ApiModel(value="com.lby.psychology.model.pojo.PsycPermission")
@Data
public class PsycPermission implements Serializable {
    /**
     * 权限id
     */
    @ApiModelProperty(value="权限id")
    private Integer permissionId;

    /**
     * 权限路径
     */
    @ApiModelProperty(value="权限路径")
    private String permissionUrl;

    /**
     * 权限类型 10 接口 20 页面
     */
    @ApiModelProperty(value="权限类型 10 接口 20 页面")
    private Short permissionType;

    /**
     * 认证级别0 匿名 1 登录
     */
    @ApiModelProperty(value="认证级别0 匿名 1 登录")
    private Short authType;

    private static final long serialVersionUID = 1L;
}