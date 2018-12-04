package com.saguadopro.clietenweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableEurekaClient
public class ClietenwebApplication {

    //    Para funcionar en un tomcat
//    extends SpringBootServletInitializer
//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//        return application.sources(ClietenwebApplication.class);
//
//    }

    public static void main(String[] args) {
        SpringApplication.run(ClietenwebApplication.class, args);
    }
}
