package com.lby.psychology;

import com.lby.psychology.util.MinioUtil;
import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileInputStream;

@SpringBootApplication
public class PsychologyApplication {

    @Autowired
    private MinioUtil minioUtil;

    public static void main(String[] args) {
        SpringApplication.run(PsychologyApplication.class, args);
    }

//    @PostConstruct
    public void test() throws Exception {

        File file = new File("C:\\Users\\coding\\Desktop\\coding\\psychology\\src\\main\\resources\\static\\assets\\img\\qq-logo.jpg");
        FileInputStream fileInputStream = new FileInputStream(file);
        // MockMultipartFile(String name, @Nullable String originalFilename, @Nullable String contentType, InputStream contentStream)
        // 其中originalFilename,String contentType 旧名字，类型  可为空
        // ContentType.APPLICATION_OCTET_STREAM.toString() 需要使用HttpClient的包
        MultipartFile multipartFile = new MockMultipartFile("copy"+file.getName(),file.getName(), MediaType.APPLICATION_OCTET_STREAM.toString(),fileInputStream);


        String response = minioUtil.uploadFile(multipartFile,"psychology");

        System.out.println(response);

    }


    // SpringBoot2.x配置HTTPS,并实现HTTP访问自动转向HTTPS
    @Bean
    public ServletWebServerFactory servletContainer() {

        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory(){
            @Override
            protected void postProcessContext(Context context) {
                SecurityConstraint securityConstraint = new SecurityConstraint();
                securityConstraint.setUserConstraint("CONFIDENTIAL");
                SecurityCollection collection = new SecurityCollection();
                collection.addPattern("/*");
                securityConstraint.addCollection(collection);
                context.addConstraint(securityConstraint);
            }
        };
        tomcat.addAdditionalTomcatConnectors(httpConnector());
        return tomcat;
    }

    @Bean
    public Connector httpConnector() {
        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
        connector.setScheme("http");
        connector.setPort(9527); // 监听Http的端口
        connector.setSecure(false);
        connector.setRedirectPort(9528); // 监听Http端口后转向Https端口
        return connector;
    }
}
