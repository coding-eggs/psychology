package com.lby.psychology.controller;

import com.lby.psychology.model.ResponseData;
import com.lby.psychology.model.security.SecurityPsycUser;
import com.lby.psychology.model.vo.RegisteredUserVo;
import com.lby.psychology.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@Api
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    private IUserService userService;

    @Autowired
    private ApplicationContext context;

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

    @ApiOperation(value = "获取公钥")
    @GetMapping("/publicKey")
    public ResponseData<String> getPublicKey() throws Exception {

        Resource keyStore = this.context.getResource("classpath:coding.keystore");
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(keyStore, "coding".toCharArray());
        KeyPair keyPair =  keyStoreKeyFactory.getKeyPair("coding.keystore","coding".toCharArray());
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();

        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        return new ResponseData<>("-----BEGIN PUBLIC KEY-----\n" + new String(Base64.encode(publicKey.getEncoded()))
                + "\n-----END PUBLIC KEY-----");
    }
}
