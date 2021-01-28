package com.lby.psychology.config;

import com.lby.psychology.model.QQAccessToken;
import com.lby.psychology.model.QQOpenId;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestTemplate;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class QQAuthenticationFilter extends AbstractAuthenticationProcessingFilter {


    /**
     * oauth2 认证回调返回得code
     */
    private final static String CODE = "code";


    /**
     * oauth2 获取token得返回类型
     */
    private final static String FMT = "json";

    /**
     * grant_type 授权码模式
     */
    private final static String GRANT_TYPE = "authorization_code";

    /**
     * client_id 由腾讯提供(即AppId)
     */
    private static final String CLIENT_ID = "101583722";

    /**
     * client_secret 由腾讯提供(即App Key)
     */
    private final static String CLIENT_SECRET = "e7a157c1c7228e7d08c332b3d6afeedc";


    /**
     * redirect_uri 回调地址，这里我们随便写个地址 只要是本项目就好，被filter直接拦截住
     */
    private final static String REDIRECT_URI = "http://ailuoli.cn:9527/qqlogin/success";

    /**
     * 获取 access_token_url 的 url
     */
    private final static String ACCESS_TOKEN_URL = "https://graph.qq.com/oauth2.0/token";


    /**
     * 获取 OpenID url地址
     */
    private final static String OPENID_URL = "https://graph.qq.com/oauth2.0/me?access_token=%s&fmt=%s";


    /**
     * 获取 token 的地址拼接
     */
    private final static String TOKEN_ACCESS_API = "%s?grant_type=%s&client_id=%s&client_secret=%s&code=%s&redirect_uri=%s&fmt=%s";


    private final RestTemplate restTemplate;

    public QQAuthenticationFilter(String defaultFilterProcessesUrl,RestTemplate restTemplate) {
        super(new AntPathRequestMatcher(defaultFilterProcessesUrl,"GET"));
        this.restTemplate = restTemplate;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException {
        // 回调地址为get  ，直接获取返回得code
        String code = httpServletRequest.getParameter(CODE);

        String accessTokenUrl = String.format(TOKEN_ACCESS_API,ACCESS_TOKEN_URL,GRANT_TYPE,CLIENT_ID,CLIENT_SECRET,code,REDIRECT_URI,FMT);

        QQAccessToken qqAccessToken = restTemplate.getForObject(accessTokenUrl, QQAccessToken.class);

        UsernamePasswordAuthenticationToken authenticationToken = null;
        if(!ObjectUtils.isEmpty(qqAccessToken)){

            //获取openId
            QQOpenId qqOpenId = restTemplate.getForObject(String.format(OPENID_URL,qqAccessToken.getAccess_token(),FMT), QQOpenId.class);

            if(!ObjectUtils.isEmpty(qqOpenId)) {
                log.info("获取到得openId :{}", qqOpenId.getOpenid());
                // 将 accessToken 对象作为用户名,openId 作为密码
                authenticationToken = new UsernamePasswordAuthenticationToken(qqAccessToken,qqOpenId);
            }
        }
        return this.getAuthenticationManager().authenticate(authenticationToken);
    }

}
