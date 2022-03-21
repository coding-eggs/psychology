package com.lby.psychology.config;

import com.alipay.easysdk.base.oauth.models.AlipaySystemOauthTokenResponse;
import com.alipay.easysdk.factory.Factory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class AlipayAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private OtherLoginConfig otherLoginConfig;


    public AlipayAuthenticationFilter(String defaultFilterProcessesUrl,OtherLoginConfig config) {
        super(new AntPathRequestMatcher(defaultFilterProcessesUrl,"GET"));
        this.otherLoginConfig = config;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException {
        // 回调地址为get  ，直接获取返回得code
        String code = httpServletRequest.getParameter("auth_code");

        UsernamePasswordAuthenticationToken authenticationToken = null;
        //初始化配置
        Factory.setOptions(otherLoginConfig.getAlipayOptions());
        try {
            //获取token
            AlipaySystemOauthTokenResponse response = Factory.Base.OAuth().getToken(code);
            authenticationToken = new UsernamePasswordAuthenticationToken(response,response.getUserId());
        } catch (Exception e) {
            e.printStackTrace();
            log.info("支付宝登录失败");
        }
        return this.getAuthenticationManager().authenticate(authenticationToken);
    }


}
