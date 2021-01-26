package com.lby.psychology.model.security;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SecurityRolePermission {
    @ApiModelProperty(value = "资源路径")
    private String permissionUrl;

    @ApiModelProperty(value = "角色编码")
    private String roleCode;

}
