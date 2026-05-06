package com.project.weatherApp.util;

import com.project.weatherApp.exception.InvalidSessionException;
import com.project.weatherApp.model.Session;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class SessionUtils {
    public static final int SESSION_DURATION_SECONDS = 60 * 60 * 3;
    public static final ZoneId ZONE_MOSCOW = ZoneId.of("Europe/Moscow");

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
        return LocalDateTime.now(ZONE_MOSCOW).isAfter(session.getExpiresAt());
    }

    private static LocalDateTime generateExpiresAt(){
        return LocalDateTime.now(ZONE_MOSCOW).plusSeconds(SESSION_DURATION_SECONDS);
    }
}
