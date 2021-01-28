package com.lby.psychology.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("com.lby.psychology.model.vo.RolePermissionVo")
public class RolePermissionVo {

    /**
     * 角色id
     */
    @ApiModelProperty(value="角色id")
    private Integer roleId;

    /**
     * 角色编码
     */
    @ApiModelProperty(value="角色编码")
    private String roleCode;

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

}
