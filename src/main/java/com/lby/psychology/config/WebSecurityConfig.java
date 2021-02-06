package com.lby.psychology.config;

import com.lby.psychology.config.handler.LoginFailureHandler;
import com.lby.psychology.config.handler.LoginSuccessHandler;
import com.lby.psychology.config.handler.RequestAccessDeniedHandler;
import com.lby.psychology.config.handler.UserSessionInformationExpiredStrategy;
import com.lby.psychology.mapper.PsycRoleMapper;
import com.lby.psychology.mapper.PsycUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.web.client.RestTemplate;

import javax.sql.DataSource;
import java.security.KeyPair;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${spring.security.remember.timeout}")
    private Integer rememberTime;


    @Autowired
    private ApplicationContext context;

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
    private RequestAccessDeniedHandler requestAccessDeniedHandler;

    @Autowired
    private UserSessionInformationExpiredStrategy userSessionInformationExpiredStrategy;

    /**
    * 记住我功能会将token存储在数据库，自动
    * @author myk
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
                .antMatchers("/test/hello","/user/email/check","/user/email/registered","/user/publicKey").permitAll()
                .antMatchers("/assets/**","/login.html","/register.html").permitAll()
                .antMatchers("/doc.html","/swagger-resources","/v2/api-docs","/swagger-ui.html","/swagger-resources/configuration/ui","/swagger-resources/configuration/security","/webjars/**").permitAll()
                .anyRequest()
                .access("@securityAuthorityDecision.hasPermission(request,authentication)");
        http
                .addFilterAt(qqAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
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
                .accessDeniedHandler(requestAccessDeniedHandler);

        http.sessionManagement()
                .sessionFixation()
                .changeSessionId()
                .maximumSessions(1)
                .maxSessionsPreventsLogin(false)
                .expiredSessionStrategy(userSessionInformationExpiredStrategy);

    }

    private QQAuthenticationFilter qqAuthenticationFilter() {
        QQAuthenticationFilter authenticationFilter = new QQAuthenticationFilter("/qqlogin/success",restTemplate);
        authenticationFilter.setAuthenticationManager(new QQAuthenticationManager(psycRoleMapper,psycUserMapper,restTemplate));
        return authenticationFilter;
    }

    @Bean
    public KeyPair keyPair(){
        Resource keyStore = this.context.getResource("classpath:coding.keystore");
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(keyStore, "coding".toCharArray());
        return keyStoreKeyFactory.getKeyPair("coding.keystore","coding".toCharArray());
    }


}
