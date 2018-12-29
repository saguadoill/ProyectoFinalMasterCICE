package com.saguadopro.gestionusuarios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Microservicio encargado de la gestion de los usuarios
 */
@SpringBootApplication
@EnableSwagger2
@EnableEurekaClient
@EnableFeignClients
public class UsuariosMicroservice {

    public static void main(String[] args) {
        SpringApplication.run(UsuariosMicroservice.class, args);
    }
}
