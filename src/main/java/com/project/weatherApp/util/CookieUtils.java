package com.project.weatherApp.util;

import com.project.weatherApp.dto.UserDto;
import com.project.weatherApp.dto.WeatherResponseDto;
import com.project.weatherApp.model.Session;
import com.project.weatherApp.service.LocationService;
import com.project.weatherApp.service.SessionService;
import com.project.weatherApp.service.UserService;
import jakarta.servlet.http.Cookie;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.project.weatherApp.util.SessionUtils.SESSION_DURATION_SECONDS;

@Component
@RequiredArgsConstructor
public class CookieUtils {
    public Cookie createCookie(UUID sessionId){
        Cookie cookie = new Cookie("MY_SESSION_ID", sessionId.toString());
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(SESSION_DURATION_SECONDS);
        return cookie;
    }

    public Cookie deleteCookie(){
        Cookie cookie = new Cookie("MY_SESSION_ID", "");
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(0);
        return cookie;
    }
}
