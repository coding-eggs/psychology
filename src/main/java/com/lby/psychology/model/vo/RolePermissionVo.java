package com.lby.psychology.model.vo;

import com.lby.psychology.model.pojo.PsycPermission;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class RolePermissionVo extends PsycPermission {

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

}
