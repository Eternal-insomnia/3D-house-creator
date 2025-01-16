package com.example.backend.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.backend.service.UserService;
import com.example.backend.service.UserServiceImpl;

@Configuration
public class UserConfig {
    
    @Bean
    public UserService userBean() {
        return new UserServiceImpl();
    }

    @Bean
    public ModelMapper modelMapperBean() {
        return new ModelMapper();
    }
}
