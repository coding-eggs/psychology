package com.lby.psychology.controller;

import com.lby.psychology.model.ResponseData;
import com.lby.psychology.model.security.SecurityPsycUser;
import com.lby.psychology.model.vo.RegisteredUserVo;
import com.lby.psychology.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Api
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    private IUserService userService;

    @ApiOperation(value = "获取当前登录用户信息")
    @GetMapping("/getUserInfo")
    public ResponseData<SecurityPsycUser> getUserInfo(Authentication authentication){
        SecurityPsycUser securityPsycUser = (SecurityPsycUser) authentication.getPrincipal();
        //置空密码
        securityPsycUser.setPassword("");
        return new ResponseData<>(securityPsycUser);
    }

    @ApiOperation(value = "根据邮箱进行注册")
    @PostMapping("/email/registered")
    public ResponseData<Boolean> emailRegistered(@RequestBody @Validated RegisteredUserVo registeredUserVo){
        ResponseData<Boolean> responseData = new ResponseData<>(false);
        if(userService.registeredPsycUser(registeredUserVo)){
            responseData.setData(true);
        }
        return responseData;
    }

    @ApiOperation(value = "验证邮箱")
    @PostMapping("/email/check")
    public ResponseData<Boolean> emailCheck(@RequestBody RegisteredUserVo registeredUserVo){
        userService.checkEmailRegistered(registeredUserVo);
        return new ResponseData<>(true);
    }
}
