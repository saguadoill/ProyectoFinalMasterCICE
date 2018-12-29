package com.saguadopro.fotosms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Microservicio encargado de gesttionar las imagenes/fotos de los usuarios y apartamentos
 * @author Sergio Aguado
 * @version 0.1
 */
@SpringBootApplication
@EnableSwagger2
@EnableEurekaClient
public class FotosMicroservice {

    public static void main(String[] args) {
        SpringApplication.run(FotosMicroservice.class, args);
    }

}

