package com.lby.psychology;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import java.security.KeyPair;

@SpringBootApplication
public class PsychologyApplication {


    @Autowired
    private ApplicationContext context;

    public static void main(String[] args) {
        SpringApplication.run(PsychologyApplication.class, args);
    }

    @Bean
    public KeyPair keyPair(){
        Resource keyStore = this.context.getResource("classpath:coding.keystore");
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(keyStore, "coding".toCharArray());
        return keyStoreKeyFactory.getKeyPair("coding.keystore","coding".toCharArray());
    }
    // SpringBoot2.x配置HTTPS,并实现HTTP访问自动转向HTTPS
//    @Bean
//    public ServletWebServerFactory servletContainer() {
//
//        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory(){
//            @Override
//            protected void postProcessContext(Context context) {
//                SecurityConstraint securityConstraint = new SecurityConstraint();
//                securityConstraint.setUserConstraint("CONFIDENTIAL");
//                SecurityCollection collection = new SecurityCollection();
//                collection.addPattern("/*");
//                securityConstraint.addCollection(collection);
//                context.addConstraint(securityConstraint);
//            }
//        };
//        tomcat.addAdditionalTomcatConnectors(httpConnector());
//        return tomcat;
//    }
//
//    @Bean
//    public Connector httpConnector() {
//        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
//        connector.setScheme("http");
//        connector.setPort(9527); // 监听Http的端口
//        connector.setSecure(false);
//        connector.setRedirectPort(9528); // 监听Http端口后转向Https端口
//        return connector;
//    }
}
