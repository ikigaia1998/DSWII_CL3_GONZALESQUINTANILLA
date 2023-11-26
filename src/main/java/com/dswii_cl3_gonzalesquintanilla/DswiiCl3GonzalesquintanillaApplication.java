package com.dswii_cl3_gonzalesquintanilla;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class DswiiCl3GonzalesquintanillaApplication {

    public static void main(String[] args) {SpringApplication.run(DswiiCl3GonzalesquintanillaApplication.class, args);}

    @Bean
    public WebMvcConfigurer configCorsGlobal() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("api/v1/**")
                        .allowedMethods("GET", "POST", "PUT")
                        .allowedOrigins("https://www.cibertec.edu.pe");
            }
        };
    }
}
