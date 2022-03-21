package com.lby.psychology.model.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class PsycChangePasswordVo {

    private Long userId;

    @NotBlank
    private String oldPassword;

    @NotBlank
    private String newPassword;

}
