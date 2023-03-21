package com.SpringBootProject.app.controller;

import com.SpringBootProject.app.api.TokenApiDelegate;
import com.SpringBootProject.app.model.JWTResponseDTO;
import com.SpringBootProject.app.model.ResponseContainerDTO;
import com.SpringBootProject.app.service.JWTService;
import com.SpringBootProject.app.service.UserAuthenticationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public class TokenController extends BaseController implements TokenApiDelegate {
    private static final Logger LOGGER = LoggerFactory.getLogger(TokenController.class);

    private final UserAuthenticationService userAuthenticationService;
    private final JWTService jwtService;

    public TokenController(UserAuthenticationService theUserAuthenticationService,
                           JWTService theJwtService) {
        userAuthenticationService = theUserAuthenticationService;
        jwtService = theJwtService;
    }

    public ResponseEntity<ResponseContainerDTO> login(String username,
                                                      String password) {
        Long start = System.currentTimeMillis();
        LOGGER.debug("login");
        ResponseContainerDTO response = new ResponseContainerDTO();
        try {
            Map<String, String> tokens = userAuthenticationService.login(username, password);
            return responseTokens(start, response, tokens);
        } catch (Exception e) {
            LOGGER.error(String.format("Login failed for user: \"%s\" pwd: \"%s\" ", username, password), e);
            return buildErrorResponse(response, HttpStatus.BAD_REQUEST, e, "A1", start);
        }
    }

    public ResponseEntity<ResponseContainerDTO> refreshToken(String authorization) {
        Long start = System.currentTimeMillis();
        ResponseContainerDTO response = new ResponseContainerDTO();
        LOGGER.debug("refreshToken");
        try {
            Map<String, String> tokens = jwtService.validateRefreshToken(authorization);
            assert tokens != null;
            return responseTokens(start, response, tokens);
        } catch (Exception e) {
            LOGGER.error(String.format("refreshToken failed for token: \"%s\" ", authorization), e);
            return buildErrorResponse(response, HttpStatus.BAD_REQUEST, e, "A1", start);
        }
    }

    private ResponseEntity<ResponseContainerDTO> responseTokens(Long start,
                                                                        ResponseContainerDTO response, Map<String, String> tokens) {
        HttpHeaders headers = new HttpHeaders();
        tokens.forEach(headers::add);
        JWTResponseDTO jwtResponse = new JWTResponseDTO();
        jwtResponse.setAccessToken(tokens.get(JWTService.ACCESS_TOKEN_HEADER));
        jwtResponse.setRefreshToken(tokens.get(JWTService.REFRESH_TOKEN_HEADER));
        response.setData(jwtResponse);
        response.setMeta(buildMeta(start));
        return ResponseEntity.status(HttpStatus.ACCEPTED).headers(headers).body(response);
    }
}
