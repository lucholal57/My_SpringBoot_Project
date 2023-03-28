package com.SpringBootProject.app.Service.User;

import java.util.Map;

public interface UserAuthService {
    Map<String, String> login(final String username, final String password);
}
