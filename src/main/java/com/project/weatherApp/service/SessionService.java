package com.project.weatherApp.service;

import com.project.weatherApp.model.Session;
import com.project.weatherApp.repository.SessionRepository;
import com.project.weatherApp.repository.UserRepository;
import com.project.weatherApp.util.SessionUtils;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import java.util.UUID;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SessionService {
    private final SessionRepository sessionRepository;
    private final SessionUtils sessionUtil;

    @Transactional
    public void createSession(int userId){
        Session session = sessionUtil.createSessionEntity(userId);
        sessionRepository.save(session);
    }

    @Transactional
    public Session getSession(UUID sessionId){
        return sessionRepository.findById(sessionId);
    }

    @Transactional
    public Session findByUserId(int userId){
        return sessionRepository.findByUserId(userId).isPresent() ? sessionRepository.findByUserId(userId).get() : null;
    }
    @Transactional
    public void deleteSession(Session session) {
        if (session == null) { return; }
        else { sessionRepository.delete(session); }
    }
}
