package com.saguadopro.gestioreservas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableEurekaClient
@EnableSwagger2
@EnableFeignClients
public class ReservasMicroservice {

    public static void main(String[] args) {
        SpringApplication.run(ReservasMicroservice.class, args);
    }
}
