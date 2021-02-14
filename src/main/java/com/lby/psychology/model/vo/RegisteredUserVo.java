package com.lby.psychology.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class RegisteredUserVo {

    @ApiModelProperty(value = "用户id")
    private Long userId;

    @ApiModelProperty(value = "用户名")
    private String username;

    @NotNull(message = "nickname 昵称不能为空")
    @ApiModelProperty(value = "昵称")
    private String nickname;

    @NotNull(message = "邮箱不能为空")
    @Pattern(regexp = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$",message = "邮箱不符合格式")
    @ApiModelProperty(value = "邮箱")
    private String email;

    @NotNull(message = "密码不能为空")
    @Min(value = 6,message = "密码长度不能小于6")
    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "验证码")
    private String code;

}
