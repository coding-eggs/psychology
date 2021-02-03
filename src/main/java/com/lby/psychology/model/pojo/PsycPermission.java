package com.lby.psychology.model.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * psyc_permission
 * @author myk
 */
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

    @ApiModelProperty(value = "页面展示名")
    private String pageName;

    @ApiModelProperty(value = "页面优先级")
    private Integer priority;

    @ApiModelProperty(value = "父级页面id")
    private Integer parentPageId;

    @ApiModelProperty(value = "备注")
    private String remark;


    private static final long serialVersionUID = 1L;
}
