package com.aynur.stockbot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // Bütün API yolları üçün (məs: /api/watchlist, /api/users və s.)
                        .allowedOrigins("http://localhost:5173") // Frontend-in işlədiyi ünvan
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // İcazə verilən sorğu növləri
                        .allowedHeaders("*") // Bütün başlıqlara (headers) icazə ver
                        .allowCredentials(true); // Əgər cookie və ya seans istifadə olunacaqsa
            }
        };
    }
}

