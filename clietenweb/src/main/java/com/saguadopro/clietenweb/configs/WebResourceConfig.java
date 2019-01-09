package com.saguadopro.clietenweb.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Clase que sirve para la configuracion del path de los recursos
 */
@Configuration
@EnableWebMvc
public class WebResourceConfig implements WebMvcConfigurer {

    /**
     * Métod para añadir carpetas al path de recursos
     *
     * @param registry - ResourceHandlerRegistry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(
                "/img/**",
                "/css/**",
                "/js/**",
                "/fonts/**",
                "/pages/**")
                .addResourceLocations(
                        "classpath:/static/img/",
                        "classpath:/static/css/",
                        "classpath:/static/js/",
                        "classpath:/static/fonts/",
                        "classpath:/templates/pages/");
    }

}