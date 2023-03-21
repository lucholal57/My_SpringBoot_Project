package com.SpringBootProject.app.conf;

import com.SpringBootProject.app.controller.CartController;
import com.SpringBootProject.app.controller.TokenController;
import com.SpringBootProject.app.controller.UserController;
import com.SpringBootProject.app.repository.UserRepository;
import com.SpringBootProject.app.service.*;
import com.SpringBootProject.app.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
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

    @Bean
    public TokenController getTokenController(UserAuthenticationService userAuthenticationService,
                                              JWTService jwtService) {
        return new TokenController(userAuthenticationService, jwtService);
    }

    @Bean
    public JWTService getJwtService(UserDetailsService userDetailsService, JwtTokenUtil jwtTokenUtil) {
        return new JWTServiceImpl(userDetailsService, jwtTokenUtil);
    }

    @Bean
    public UserAuthenticationService getUserAuthenticationService(PasswordEncoder passwordEncoder,
                                                                  JWTService jwtService, UserDetailsService userDetailsService) {
        return new UserAuthenticationServiceImpl(passwordEncoder, jwtService, userDetailsService);
    }

    @Bean
    public CartController getCartController(){
        return new CartController();
    }

}
