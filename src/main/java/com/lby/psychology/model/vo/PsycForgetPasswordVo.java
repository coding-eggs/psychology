package com.lby.psychology.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class PsycForgetPasswordVo {
    @ApiModelProperty(value = "验证码")
    private String code;

    @NotBlank
    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "新密码")
    private String newPassword;

}
