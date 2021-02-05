package com.lby.psychology.config.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lby.psychology.model.ResponseData;
import com.lby.psychology.model.enums.EnumResponseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
* 已认证用户无权限处理器
* @author myk
* @date 2021/1/27 23:34
*/
@Component
public class RequestAccessDeniedHandler implements AccessDeniedHandler {
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        ResponseData<String> responseData = new ResponseData<>();
        responseData.setCode(EnumResponseType.NON_AUTH_ENTRY_POINT.getCode());
        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        httpServletResponse.getWriter().write(objectMapper.writeValueAsString(responseData));
    }
}
