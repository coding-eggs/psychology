package com.lby.psychology.model.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Data;

/**
 * psyc_user_role_rlt
 * @author
 */
@ApiModel(value="com.lby.psychology.model.pojo.PsycUserRoleRlt")
@Data
public class PsycUserRoleRlt implements Serializable {
    /**
     * id
     */
    @ApiModelProperty(value="id")
    private Integer userRoleRltId;

    /**
     * 用户id
     */
    @ApiModelProperty(value="用户id")
    private Long userId;

    /**
     * 角色id
     */
    @ApiModelProperty(value="角色id")
    private Integer roleId;

    /**
     * 备注
     */
    @ApiModelProperty(value="备注")
    private String remark;

    private static final long serialVersionUID = 1L;
}
