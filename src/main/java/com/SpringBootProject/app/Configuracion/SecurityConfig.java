package com.SpringBootProject.app.Configuracion;

import com.SpringBootProject.app.Security.CustomAuthorizationFilter;
import com.SpringBootProject.app.Utils.JwtTokenUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityConfig.class);

    @Bean
    public JwtTokenUtil getJwtTokenUtil(@Value("${jwt.secret}") String secret) {
        return new JwtTokenUtil(secret);
    }

    @Bean
    public CustomAuthorizationFilter getCustomAuthorizationFilter(ObjectMapper objectMapper, PasswordEncoder encoder,
                                                                  UserDetailsService userDetailsService, JwtTokenUtil jwtTokenUtil,
                                                                  @Value("${api.basePath}") String basePath, @Value("${api.loginPath}") String loginPath,
                                                                  @Value("${api.refreshTokenPath}") String refreshTokenPath){
        return new CustomAuthorizationFilter(objectMapper, userDetailsService, jwtTokenUtil, basePath, loginPath,
                refreshTokenPath);
    }
    @Bean
    public SecurityFilterChain getSecurityFilterChain(HttpSecurity http, CustomAuthorizationFilter customAuthorizationFilter,
                                                      @Value("${api.basePath}") String basePath) throws Exception {
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests().antMatchers(basePath + "/users/**").permitAll();
        http.authorizeRequests().antMatchers(basePath + "/products/**").permitAll();
        http.authorizeRequests().antMatchers(basePath + "/carts/**").permitAll();
        http.authorizeRequests().antMatchers(basePath + "/token/login").permitAll();
        http.authorizeRequests().antMatchers("/swagger-ui/**").permitAll();
        http.authorizeRequests().anyRequest().permitAll();
        http.addFilterBefore(customAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
