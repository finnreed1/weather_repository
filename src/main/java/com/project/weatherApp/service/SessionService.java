package com.project.weatherApp.service;

import com.project.weatherApp.dto.LoginUserDto;
import com.project.weatherApp.model.Session;
import com.project.weatherApp.model.User;
import com.project.weatherApp.repository.SessionRepository;
import com.project.weatherApp.repository.UserRepository;
import com.project.weatherApp.util.SessionUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class SessionService {
    private static final String COOKIE_NAME = "MY_SESSION_ID";
    public final SessionRepository sessionRepository;
    public final UserRepository userRepository;
    public final SessionUtil sessionUtil;

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

//
//    public void closeSession(HttpServletRequest request, HttpServletResponse response) {
//        String sessionIdStr = extractSessionId(request);
//        if (sessionIdStr != null) {
//            int sessionId = Integer.parseInt(sessionIdStr);
//            sessionRepository.deleteById(sessionId);
//            Cookie cookie = new Cookie(COOKIE_NAME, "");
//            cookie.setPath("/");
//            cookie.setMaxAge(0);
//            response.addCookie(cookie);

//        }

//    }

    private static String extractSessionId(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) { return null; }
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (COOKIE_NAME.equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
}
