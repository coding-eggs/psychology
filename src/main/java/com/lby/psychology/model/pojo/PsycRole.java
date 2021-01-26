package com.lby.psychology.model.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

/**
 * psyc_role
 * @author
 */
@ApiModel(value="com.lby.psychology.model.pojo.PsycRole")
@Data
public class PsycRole implements GrantedAuthority,Serializable {
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
     * 备注
     */
    @ApiModelProperty(value="备注")
    private String remark;

    @ApiModelProperty(value = "角色状态")
    private Short roleStatus;

    private static final long serialVersionUID = 1L;

    @Override
    public String getAuthority() {
        return roleCode;
    }
}
