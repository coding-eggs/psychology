package com.lby.psychology.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Api
@RestController
public class TestController {


    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String from;

    @GetMapping("/test/hello")
    @ApiOperation("hello")
    public void hello(){

        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("Psychology Manage System 请求验证码 ");
        message.setFrom(from);
        message.setTo("wsailuoli@outlook.com");
        message.setText("验证码为"+(int)((Math.random()*9+1)*100000));
        javaMailSender.send(message);
    }
}
