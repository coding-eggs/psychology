package com.lby.psychology.config.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lby.psychology.model.ResponseData;
import com.lby.psychology.model.enums.EnumResponseType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import java.io.IOException;

@Slf4j
@Component
public class UserSessionInformationExpiredStrategy implements SessionInformationExpiredStrategy {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent sessionInformationExpiredEvent) throws IOException {

        String type = sessionInformationExpiredEvent.getRequest().
                getHeader("X-Requested-With")==null?"":sessionInformationExpiredEvent.getRequest().getHeader("X-Requested-With");
        if("XMLHttpRequest".equals(type)) {
            //设置响应头为重定向
            ResponseData<String> responseData = new ResponseData<>();
            responseData.setCode(EnumResponseType.USER_SESSION_EXPIRED_CON.getCode());
            sessionInformationExpiredEvent.getResponse().setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            sessionInformationExpiredEvent.getResponse().getWriter().write(objectMapper.writeValueAsString(responseData));
        }else {
            log.info("并发导致 session 过期");
            //清除session
            Cookie cookie = new Cookie("JSESSIONID",null);
            cookie.setMaxAge(0);
            cookie.setPath("/");
            sessionInformationExpiredEvent.getResponse().addCookie(cookie);
            sessionInformationExpiredEvent.getResponse().sendRedirect(sessionInformationExpiredEvent.getRequest().getContextPath()+"/login.html");
        }
    }
}
