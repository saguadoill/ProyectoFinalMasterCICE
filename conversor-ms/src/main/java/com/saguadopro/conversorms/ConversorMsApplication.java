package com.saguadopro.conversorms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Applicación encargada de convertir los objetos de Entity a DTO y viceversa
 * @see {@link com.saguadopro.conversorms.services.EntityToDtoService} y @see {@link com.saguadopro.conversorms.services.DtoToEntityService}
 * @version 0.1
 */
@SpringBootApplication
@EnableFeignClients
@EnableEurekaClient
@EnableSwagger2
public class ConversorMsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConversorMsApplication.class, args);
    }

}

