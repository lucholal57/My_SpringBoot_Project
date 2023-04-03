package com.SpringBootProject.app.controller;

import com.SpringBootProject.app.api.TokenApiDelegate;
import com.SpringBootProject.app.Service.JWT.JWTService;
import com.SpringBootProject.app.Service.User.UserAuthService;
import com.SpringBootProject.app.model.JWTResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public class TokenController implements TokenApiDelegate {
    private static final Logger LOGGER = LoggerFactory.getLogger(TokenController.class);

    private final UserAuthService userAuthService;
    private final JWTService jwtService;

    public TokenController(UserAuthService theUserAuthService,
                           JWTService theJwtService) {
        userAuthService = theUserAuthService;
        jwtService = theJwtService;
    }

    public ResponseEntity<JWTResponseDTO> login(String username,
                                                String password) {
        LOGGER.debug("login");
        JWTResponseDTO response = new JWTResponseDTO();
        try {
            Map<String, String> tokens = userAuthService.login(username, password);
            return responseTokens(response, tokens);
        } catch (Exception e) {
            LOGGER.error(String.format("Login failed for user: \"%s\" pwd: \"%s\" ", username, password), e);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
        }
    }

    public ResponseEntity<JWTResponseDTO> refreshToken(String authorization) {
        JWTResponseDTO response = new JWTResponseDTO();
        LOGGER.debug("refreshToken");
        try {
            Map<String, String> tokens = jwtService.validateRefreshToken(authorization);
            assert tokens != null;
            return responseTokens(response, tokens);
        } catch (Exception e) {
            LOGGER.error(String.format("refreshToken failed for token: \"%s\" ", authorization), e);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
        }
    }

    private ResponseEntity<JWTResponseDTO> responseTokens(JWTResponseDTO response,
                                                                Map<String, String> tokens) {
        HttpHeaders headers = new HttpHeaders();
        tokens.forEach(headers::add);
        JWTResponseDTO jwtResponse = new JWTResponseDTO();
        jwtResponse.setAccessToken(tokens.get(JWTService.ACCESS_TOKEN_HEADER));
        jwtResponse.setRefreshToken(tokens.get(JWTService.REFRESH_TOKEN_HEADER));
        response.setAccessToken(String.valueOf(jwtResponse));
        return ResponseEntity.status(HttpStatus.ACCEPTED).headers(headers).body(response);
    }

}
