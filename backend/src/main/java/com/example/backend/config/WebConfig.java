package com.example.backend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Для всех эндпоинтов
                .allowedOrigins("http://localhost:5173") // Разрешённый адрес фронтенда
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Разрешённые методы
                .allowedHeaders("*") // Разрешённые заголовки
                .allowCredentials(true); // Если используется авторизация (cookies)
    }
}
