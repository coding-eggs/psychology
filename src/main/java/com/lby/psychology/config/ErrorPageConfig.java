package com.lby.psychology.config;

import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

/**
* 错误页面配置类
* @author lby
*/
@Configuration
public class ErrorPageConfig {

    @Bean
    public WebServerFactoryCustomizer<ConfigurableWebServerFactory> webServerFactoryWebServerFactoryCustomizer(){

        return (factory -> {
            factory.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND,"/404"),new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR,"/500"));
        });

    }

}
