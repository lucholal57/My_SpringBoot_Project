package com.SpringBootProject.app.service.user;

import java.util.Map;

public interface UserAuthService {
    Map<String, String> login(final String username, final String password);
}
