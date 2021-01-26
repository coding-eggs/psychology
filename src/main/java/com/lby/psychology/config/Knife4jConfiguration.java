package com.lby.psychology.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

@Configuration
@EnableSwagger2WebMvc
public class Knife4jConfiguration {

    @Bean(value = "defaultApi2")
    public Docket defaultApi2() {

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .title("psychology 心理测试系统 API 文档")
                        .description("psychology 心理测试系统 API 文档")
                        .termsOfServiceUrl("http://ailuoli.cn/")
                        .contact(new Contact("lby","","526518441@qq.com"))
                        .version("1.0")
                        .build())
                //分组名称
                .groupName("API")
                .select()
                //这里指定Controller扫描包路径
                .apis(RequestHandlerSelectors.basePackage("com.lby.psychology.controller"))
                .paths(PathSelectors.any())
                .build();
    }

}
