package com.saguadopro.gestioreservas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class GestioReservasApplication {

    public static void main(String[] args) {
        SpringApplication.run(GestioReservasApplication.class, args);
    }
}
