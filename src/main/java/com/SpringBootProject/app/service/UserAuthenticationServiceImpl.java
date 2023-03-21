package com.SpringBootProject.app.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Map;

public class UserAuthenticationServiceImpl implements UserAuthenticationService {
    private JWTService jwtService;
    private PasswordEncoder passwordEncoder;
    private UserDetailsService userDetailService;


    public UserAuthenticationServiceImpl(PasswordEncoder thePasswordEncoder, JWTService theJwtService,
                                         UserDetailsService theUserDetailsService) {
        passwordEncoder = thePasswordEncoder;
        jwtService = theJwtService;
        userDetailService = theUserDetailsService;
    }


    @Override
    public Map<String, String> login(String username, String password) {
        UserDetails optUser = userDetailService.loadUserByUsername(username);
        if (!passwordEncoder.matches(password, optUser.getPassword())) {
            throw new RuntimeException("The passwords doesn't match");
        }
        User user = (User) optUser;
        return jwtService.generateTokenSet(user);
    }

}
