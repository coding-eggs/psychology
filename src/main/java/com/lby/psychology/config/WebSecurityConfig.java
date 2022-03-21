package com.lby.psychology.config;

import com.alipay.api.AlipayClient;
import com.lby.psychology.config.handler.*;
import com.lby.psychology.mapper.PsycRoleMapper;
import com.lby.psychology.mapper.PsycUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.web.client.RestTemplate;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity(debug = false)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${spring.security.remember.timeout}")
    private Integer rememberTime;

    @Autowired
    private PsycPasswordEncoder passwordEncoder;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private PsycRoleMapper psycRoleMapper;

    @Autowired
    private PsycUserMapper psycUserMapper;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UserDetailsService securityUserDetailsService;

    @Autowired
    private LoginSuccessHandler loginSuccessHandler;

    @Autowired
    private LoginFailureHandler loginFailureHandler;

    @Autowired
    private PsycAuthenticationEntryPoint authenticationEntryPoint;



    @Autowired
    private SessionInvalidSessionStrategy invalidSessionStrategy;

    @Autowired
    private RequestAccessDeniedHandler requestAccessDeniedHandler;

    @Autowired
    private UserSessionInformationExpiredStrategy userSessionInformationExpiredStrategy;



    @Autowired
    private OtherLoginConfig loginConfig;

    @Autowired
    private AlipayClient alipayClient;

    /**
    * 记住我功能会将token存储在数据库，自动
    * @author lby
    * @date 2021/1/25 9:25
    * @return org.springframework.security.web.authentication.rememberme.PersistentTokenRepository
    */
    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        // 自动建表
//        jdbcTokenRepository.setCreateTableOnStartup(true);
        return jdbcTokenRepository;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(securityUserDetailsService).passwordEncoder(passwordEncoder);
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.headers().frameOptions().sameOrigin();

        http.csrf().disable()
                .cors().disable()
                .authorizeRequests()
                //注册和忘记密码接口忽略
                .antMatchers("/test**/**","/user/email/check","/user/email/registered","/user/publicKey","/user/forgetPassword","/user/email/forgetPassword").permitAll()
                //静态资源忽略
                .antMatchers("/assets/**","/login.html","/register.html","/forgot-password.html","/404","/500","/login").permitAll()
                //swagger，doc所需的要的路径忽略
                .antMatchers("/doc.html*/**","/swagger-resources","/v2/api-docs","/swagger-ui.html","/swagger-resources/configuration/ui","/swagger-resources/configuration/security","/webjars/**").permitAll()
                .anyRequest()
                .access("@securityAuthorityDecision.hasPermission(request,authentication)");
        http
                .addFilterAt(qqAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterAt(alipayAuthenticationFilter(),UsernamePasswordAuthenticationFilter.class)
                .formLogin()
                .loginPage("/login.html")
                .loginProcessingUrl("/user/login")
                .usernameParameter("email")
                .passwordParameter("password")
                .successHandler(loginSuccessHandler)
                .failureHandler(loginFailureHandler);
        http
                .logout()
                .logoutUrl("/user/logout")
                .logoutSuccessUrl("/login.html");

        http
                .rememberMe()
                .rememberMeParameter("rememberMe")
                .rememberMeCookieName("rememberMe")
                .tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(rememberTime);
        http
                .exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint)
                .accessDeniedHandler(requestAccessDeniedHandler);

        http.sessionManagement()
                //默认session过期
                .invalidSessionStrategy(invalidSessionStrategy)
                //同一用户的在系统中的最大session数，默认1
                .maximumSessions(-1)
                //并发session过期
                .expiredSessionStrategy(userSessionInformationExpiredStrategy);

    }

    private QQAuthenticationFilter qqAuthenticationFilter() {
        QQAuthenticationFilter authenticationFilter = new QQAuthenticationFilter("/qqlogin/success",restTemplate);
        authenticationFilter.setAuthenticationManager(new QQAuthenticationManager(psycRoleMapper,psycUserMapper,restTemplate));
        SavedRequestAwareAuthenticationSuccessHandler authenticationSuccessHandler = new SavedRequestAwareAuthenticationSuccessHandler();
        authenticationSuccessHandler.setDefaultTargetUrl("/profile.html");
        authenticationFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler);
        return authenticationFilter;
    }

    private AlipayAuthenticationFilter alipayAuthenticationFilter() {
        AlipayAuthenticationFilter alipayAuthenticationFilter = new AlipayAuthenticationFilter("/alipay/login",loginConfig);
        alipayAuthenticationFilter.setAuthenticationManager(new AlipayAuthenticationManager(psycRoleMapper,psycUserMapper,alipayClient));
        SavedRequestAwareAuthenticationSuccessHandler authenticationSuccessHandler = new SavedRequestAwareAuthenticationSuccessHandler();
        authenticationSuccessHandler.setDefaultTargetUrl("/profile.html");
        alipayAuthenticationFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler);
        return alipayAuthenticationFilter;
    }




}
