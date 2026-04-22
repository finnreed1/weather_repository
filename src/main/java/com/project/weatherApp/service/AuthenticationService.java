package com.project.weatherApp.service;

import com.project.weatherApp.dto.LoginUserDto;
import com.project.weatherApp.model.Session;
import com.project.weatherApp.util.SessionUtil;
import lombok.RequiredArgsConstructor;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    public final SessionService sessionService;
    public final UserService userService;

    public UUID login(LoginUserDto user) {
        int userId = userService.findUserByLogin(user.getLogin()).get().getId();
        Session session = sessionService.findByUserId(userId);
        if (session != null) { sessionService.deleteSession(session); }
        sessionService.createSession(userId);
        return sessionService.findByUserId(userId).getId();
    }
}
