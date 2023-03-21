package com.SpringBootProject.app.service;

import com.SpringBootProject.app.utils.JwtTokenUtil;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.GrantedAuthority;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class JWTServiceImpl implements JWTService{
    private final JwtTokenUtil jwtTokenUtil;
    private final UserDetailsService userDetailsService;

    public JWTServiceImpl(UserDetailsService theUserDetailsService, JwtTokenUtil theJwtTokenUtil) {
        userDetailsService = theUserDetailsService;
        jwtTokenUtil = theJwtTokenUtil;
    }

    @Override
    public Map<String, String> generateTokenSet(UserDetails user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("roles", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()));
        String token = jwtTokenUtil.generateToken(user, claims, JwtTokenUtil.JWT_TOKEN_VALIDITY);
        String refreshToken = jwtTokenUtil.generateToken(user, new HashMap<>(), JwtTokenUtil.JWT_REFRESH_TOKEN_VALIDITY);
        Map<String, String> tokens = new HashMap<>();
        tokens.put(ACCESS_TOKEN_HEADER, token);
        tokens.put(REFRESH_TOKEN_HEADER, refreshToken);
        return tokens;
    }

    @Override
    public Map<String, String> validateRefreshToken(String refreshToken) {
        String username = jwtTokenUtil.getUsernameFromToken(refreshToken);
        UserDetails user = userDetailsService.loadUserByUsername(username);
        return generateTokenSet(user);
    }
}
