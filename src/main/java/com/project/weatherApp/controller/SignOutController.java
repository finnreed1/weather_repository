package com.project.weatherApp.controller;

import com.project.weatherApp.service.SessionService;
import com.project.weatherApp.util.CookieUtils;
import com.project.weatherApp.util.SessionUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.project.weatherApp.model.Session;

import java.util.UUID;

@Controller
@RequiredArgsConstructor
@RequestMapping("/logout")
public class SignOutController {
    @Autowired
    private SessionService sessionService;

    @Autowired
    private CookieUtils cookieUtils;

    @PostMapping
    public String signOut(@CookieValue("MY_SESSION_ID") String sessionFromCookie, HttpServletResponse response) {
        Session session = sessionService.getSession(UUID.fromString(sessionFromCookie));
        sessionService.deleteSession(session);

        Cookie cookie = cookieUtils.deleteCookie();
        response.addCookie(cookie);

        return "redirect:/home";
    }
}
