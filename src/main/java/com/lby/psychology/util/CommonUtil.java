package com.lby.psychology.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class CommonUtil {

    @Value("${spring.mail.username}")
    private String from;

    @Autowired
    private JavaMailSender javaMailSender;

    public Integer getRandomNumber(){
        return (int)((Math.random()*9+1)*100000);
    }

    public boolean sendMailCheckCode(String toUser,String code){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("Psychology Manage System 请求验证码 ");
        message.setFrom(from);
        message.setTo(toUser);
        message.setText(code);
        javaMailSender.send(message);
        return true;
    }
}
