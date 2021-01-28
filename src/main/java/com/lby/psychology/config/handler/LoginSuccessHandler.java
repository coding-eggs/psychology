package com.lby.psychology.config.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lby.psychology.mapper.PsycRoleMapper;
import com.lby.psychology.mapper.PsycUserMapper;
import com.lby.psychology.model.ResponseData;
import com.lby.psychology.model.pojo.PsycRole;
import com.lby.psychology.model.security.SecurityPsycUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.DefaultSavedRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private PsycUserMapper userMapper;

    @Autowired
    private PsycRoleMapper roleMapper;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        // 获取最初想访问的页面
        DefaultSavedRequest defaultSavedRequest = (DefaultSavedRequest) httpServletRequest.getSession().getAttribute("SPRING_SECURITY_SAVED_REQUEST");

        SecurityPsycUser securityPsycUser = (SecurityPsycUser) authentication.getPrincipal();
        securityPsycUser.setPassword("");
        //更新用户最后登录时间
        userMapper.updateLastLoginDate(securityPsycUser.getUserId());

        Map<String,Object> map = new HashMap<>();
        map.put("user",securityPsycUser);
        // 跳转到页面
        if(ObjectUtils.isEmpty(defaultSavedRequest)||"/".equals(defaultSavedRequest.getRequestURI())){
            map.put("requestURI","/index.html");
        }else{
            map.put("requestURI",defaultSavedRequest.getRequestURI());
        }
        map.put("isAdmin",false);

        securityPsycUser.getAuthorities().forEach(e->{
            if(e.getRoleCode().trim().toUpperCase(Locale.ROOT).equals("ADMIN")){
                map.put("isAdmin",true);
            }
        });
        //该用户的角色所有拥有的权限
        if(!Boolean.parseBoolean(map.get("isAdmin").toString())){
            map.put("routeList",roleMapper.selectRolePermission(securityPsycUser.getAuthorities().stream().map(PsycRole::getRoleId).collect(Collectors.toList())));
        }
        ResponseData<Map<String,Object>> responseData = new ResponseData<>(map);
        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        httpServletResponse.getWriter().write(objectMapper.writeValueAsString(responseData));
    }


}
