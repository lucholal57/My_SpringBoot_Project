package com.SpringBootProject.app.conf;

import com.SpringBootProject.app.controller.CartController;
import com.SpringBootProject.app.controller.TokenController;
import com.SpringBootProject.app.controller.UserController;
import com.SpringBootProject.app.repository.UserRepository;
import com.SpringBootProject.app.service.jwt.JWTService;
import com.SpringBootProject.app.service.jwt.JWTServiceImpl;
import com.SpringBootProject.app.service.mapper.UserMapper;
import com.SpringBootProject.app.service.mapper.UserMapperImpl;
import com.SpringBootProject.app.service.user.UserAuthService;
import com.SpringBootProject.app.service.user.UserAuthServiceImpl;
import com.SpringBootProject.app.service.user.UserAdminService;
import com.SpringBootProject.app.service.user.UserAdminServiceImpl;
import com.SpringBootProject.app.utils.JwtTokenUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

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

    /*
    Creamos el Bean de UserController para poder acceder al Controller quien es el encargado de manejar
    las peticiones, y este usa como parametro el UserAdminService quien se encarga de darnos el CRUDservice
    que creamos y lo implementa el UserAdminImpl
     */
    @Bean
    public UserController getUserController(UserAdminService userAdminService) {
        return new UserController(userAdminService);
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
    public UserAdminService getUserService(UserMapper userMapper, UserRepository userRepository) {
        return new UserAdminServiceImpl(userMapper, userRepository);
    }

    @Bean
    public TokenController getTokenController(UserAuthService userAuthService,
                                              JWTService jwtService) {
        return new TokenController(userAuthService, jwtService);
    }

    @Bean
    public JWTService getJwtService(UserDetailsService userDetailsService, JwtTokenUtil jwtTokenUtil) {
        return new JWTServiceImpl(userDetailsService, jwtTokenUtil);
    }

    @Bean
    public UserAuthService getUserAuthenticationService(PasswordEncoder passwordEncoder,
                                                        JWTService jwtService, UserDetailsService userDetailsService) {
        return new UserAuthServiceImpl(passwordEncoder, jwtService, userDetailsService);
    }

    @Bean
    public CartController getCartController(){
        return new CartController();
    }

}
