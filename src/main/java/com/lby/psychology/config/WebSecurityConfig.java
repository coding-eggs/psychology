package com.lby.psychology.config;

import com.lby.psychology.mapper.PsycRoleMapper;
import com.lby.psychology.mapper.PsycUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.web.client.RestTemplate;

import javax.sql.DataSource;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${spring.security.remember.timeout}")
    private Integer rememberTime;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private PsycRoleMapper psycRoleMapper;

    @Autowired
    private PsycUserMapper psycUserMapper;

    @Autowired
    private RestTemplate restTemplate;

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


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.headers().frameOptions().sameOrigin();

        http.csrf().disable()
                .cors().disable()
                .authorizeRequests()
                .antMatchers("/assets/**","/login.html").permitAll()
                .antMatchers("/doc.html","/swagger-resources","/v2/api-docs","/swagger-ui.html","/swagger-resources/configuration/ui","/swagger-resources/configuration/security","/webjars/**").permitAll()
                .anyRequest()
                .access("@securityAuthorityDecision.hasPermission(request,authentication)")
                .and()
                .addFilterAt(qqAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .formLogin()
                .loginPage("/login.html")
                .and()
                .rememberMe()
                .tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(rememberTime);
    }

    private QQAuthenticationFilter qqAuthenticationFilter() {
        QQAuthenticationFilter authenticationFilter = new QQAuthenticationFilter("/qqlogin/success",restTemplate);
        authenticationFilter.setAuthenticationManager(new QQAuthenticationManager(psycRoleMapper,psycUserMapper,restTemplate));
        return authenticationFilter;
    }

    public static void main(String[] args) {
        System.out.println(new BCryptPasswordEncoder().encode("123456"));
    }
}
