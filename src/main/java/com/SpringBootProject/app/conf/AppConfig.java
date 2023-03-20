package com.SpringBootProject.app.conf;

import com.SpringBootProject.app.controller.UserController;
import com.SpringBootProject.app.repository.UserRepository;
import com.SpringBootProject.app.service.UserMapper;
import com.SpringBootProject.app.service.UserMapperImpl;
import com.SpringBootProject.app.service.UserService;
import com.SpringBootProject.app.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.security.SecureRandom;

@Configuration
public class AppConfig {

    /*
    Metodo para saltar el login de Swagger
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.httpBasic().disable();
        return http.build();
    }

    @Bean
    public UserController getUserController(UserService userService) {
        return new UserController(userService);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserMapper getUserMapper(PasswordEncoder encoder){
        return new UserMapperImpl(encoder);
    }

    @Primary
    @Bean
    public UserService getUserService(UserMapper userMapper,UserRepository userRepository) {
        return new UserServiceImpl(userMapper, userRepository);
    }

}
