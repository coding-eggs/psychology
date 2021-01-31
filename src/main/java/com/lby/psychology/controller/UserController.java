package com.lby.psychology.controller;

import com.lby.psychology.model.ResponseData;
import com.lby.psychology.model.security.SecurityPsycUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @ApiOperation(value = "获取当前登录用户信息")
    @GetMapping("/getUserInfo")
    public ResponseData<SecurityPsycUser> getUserInfo(Authentication authentication){
        SecurityPsycUser securityPsycUser = (SecurityPsycUser) authentication.getPrincipal();
        //置空密码
        securityPsycUser.setPassword("");
        return new ResponseData<>(securityPsycUser);
    }
}
