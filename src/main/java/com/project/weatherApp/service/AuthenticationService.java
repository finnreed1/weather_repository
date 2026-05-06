package com.project.weatherApp.service;

import com.project.weatherApp.dto.LoginUserDto;
import com.project.weatherApp.model.Session;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final SessionService sessionService;
    private final UserService userService;

    public UUID login(LoginUserDto user) {
        int userId = userService.findUserByLogin(user.getLogin()).get().getId();
        Session session = sessionService.findByUserId(userId);
        if (session != null) { sessionService.deleteSession(session); }
        sessionService.createSession(userId);
        return sessionService.findByUserId(userId).getId();
    }
}
