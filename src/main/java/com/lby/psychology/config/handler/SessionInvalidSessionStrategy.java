package com.lby.psychology.config.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lby.psychology.model.ResponseData;
import com.lby.psychology.model.enums.EnumResponseType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
* 默认的session失效处理策略
* @author lby
*/
@Slf4j
@Component
public class SessionInvalidSessionStrategy implements InvalidSessionStrategy {

    @Autowired
    private ObjectMapper objectMapper;


    @Override
    public void onInvalidSessionDetected(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        log.info("session 过期，跳转登录。。。");
        String type = request.getHeader("X-Requested-With")==null?"":request.getHeader("X-Requested-With");
        if("XMLHttpRequest".equals(type)) {
            //设置响应头为重定向
            ResponseData<String> responseData = new ResponseData<>();
            responseData.setCode(EnumResponseType.USER_SESSION_EXPIRED.getCode());
            response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            response.getWriter().write(objectMapper.writeValueAsString(responseData));
        }
        else {
            log.info("重定向到登录页");
            //清除session
            Cookie cookie = new Cookie("JSESSIONID",null);
            cookie.setMaxAge(0);
            cookie.setPath("/");
            response.addCookie(cookie);
            response.sendRedirect(request.getContextPath()+"/login.html");
        }
    }

}
