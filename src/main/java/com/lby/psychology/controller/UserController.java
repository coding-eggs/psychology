package com.lby.psychology.controller;

import com.lby.psychology.model.ResponseData;
import com.lby.psychology.model.enums.EnumRedisPre;
import com.lby.psychology.model.security.SecurityPsycUser;
import com.lby.psychology.model.vo.RegisteredUserVo;
import com.lby.psychology.util.CommonUtil;
import com.lby.psychology.util.RedisUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@Api
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private CommonUtil commonUtil;


    @Value("${spring.mail.valicode.expired}")
    private Long expired;

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
    public ResponseData<Boolean> emailRegistered(@RequestBody RegisteredUserVo registeredUserVo){

        if(!redisUtil.get(EnumRedisPre.EMAIL_CHECK.getCode()+registeredUserVo.getEmail()).equals(registeredUserVo.getCode())){
            //TODO 抛出异常验证码不正确
        }
        //加密密码
        registeredUserVo.setPassword(passwordEncoder.encode(registeredUserVo.getPassword()));
        //TODO 插入数据库

        return new ResponseData<>(true);
    }

    @ApiOperation(value = "验证邮箱")
    @PostMapping("/email/check")
    public ResponseData<Boolean> emailCheck(@RequestBody RegisteredUserVo registeredUserVo){
        //TODO 校验用户信息
        //发送验证邮件
        Integer code = commonUtil.getRandomNumber();
        commonUtil.sendMailCheckCode(registeredUserVo.getEmail(),code.toString());
        //将验证码设置到redis内，过期时间60s
        redisUtil.set(EnumRedisPre.EMAIL_CHECK.getCode()+registeredUserVo.getEmail(),code,expired);
        return new ResponseData<>(true);
    }
}
