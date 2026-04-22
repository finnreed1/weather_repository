package com.project.weatherApp.util;

import com.project.weatherApp.exception.InvalidSessionException;
import com.project.weatherApp.model.Session;
import com.project.weatherApp.repository.SessionRepository;
import com.project.weatherApp.service.SessionService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class SessionUtil {
    public static final int SESSION_DURATION_SECONDS = 60 * 60 * 3;

    public Session createSessionEntity(int userId){
        UUID sessionId = UUID.randomUUID();
        return new Session(sessionId, userId, generateExpiresAt());
    }

    public void checkActiveSession(Session session) throws InvalidSessionException {
        if (session == null || session.getId() == null || isExpiredSession(session)){
            throw new InvalidSessionException("Invalid session");
        }
    }

    private boolean isExpiredSession(Session session) {
        return LocalDateTime.now().isAfter(session.getExpiresAt());
    }

    private static LocalDateTime generateExpiresAt(){
        return LocalDateTime.now().plusSeconds(SESSION_DURATION_SECONDS);
    }
}
