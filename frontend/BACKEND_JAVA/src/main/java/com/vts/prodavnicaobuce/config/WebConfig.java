package com.vts.prodavnicaobuce.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Dodajemo obe putanje za svaki slučaj:
        // 1. Gleda u src/main/resources/static/uploads (gde ti je sada)
        // 2. Gleda i u uploads folder u korenu projekta
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:src/main/resources/static/uploads/", "file:uploads/");
        
        // Takođe, kažemo mu da sve iz static foldera tretira kao resurse
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/");
    }
}