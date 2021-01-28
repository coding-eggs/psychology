package com.lby.psychology.config.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lby.psychology.model.ResponseData;
import com.lby.psychology.model.enums.EnumResponseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class UserSessionInformationExpiredStrategy implements SessionInformationExpiredStrategy {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent sessionInformationExpiredEvent) throws IOException {
        ResponseData<String> responseData = new ResponseData<>();
        responseData.setCode(EnumResponseType.USER_SESSION_EXPIRED.getCode());
        sessionInformationExpiredEvent.getResponse().setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        sessionInformationExpiredEvent.getResponse().getWriter().write(objectMapper.writeValueAsString(responseData));
    }
}
