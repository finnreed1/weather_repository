package com.project.weatherApp.controller;

import com.project.weatherApp.dto.LocationDto;
import com.project.weatherApp.exception.SearchLocationWithoutAuthenticationException;
import com.project.weatherApp.model.Session;
import com.project.weatherApp.service.LocationService;
import com.project.weatherApp.service.SessionService;
import com.project.weatherApp.util.ControllerUtils;
import com.project.weatherApp.util.SessionUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class SearchPageController {

    @Autowired
    private SessionService sessionService;
    @Autowired
    private SessionUtils sessionUtil;
    @Autowired
    private ControllerUtils controllerUtils;
    @Autowired
    private LocationService locationService;

    @GetMapping("/search")
    public String search(@CookieValue(value="MY_SESSION_ID", required = false, defaultValue = "") String sessionFromCookie,
                         @RequestParam("name") String name,
                         Model model){
        if (sessionFromCookie == null || sessionFromCookie.isEmpty()) {
            throw new SearchLocationWithoutAuthenticationException("You must log in to search for locations.");
        }
        Session session = sessionService.getSession(UUID.fromString(sessionFromCookie));

        sessionUtil.checkActiveSession(session);

        controllerUtils.addLocationAttributes(model, name, session);

        return "search-results";
    }

    @PostMapping("/add")
    public String addLocation(@CookieValue(value="MY_SESSION_ID", required = false, defaultValue = "") String sessionFromCookie,
                              @RequestParam("name") String name,
                              @RequestParam("longitude") String longitude,
                              @RequestParam("latitude") String latitude) {
        Session session = sessionService.getSession(UUID.fromString(sessionFromCookie));

        sessionUtil.checkActiveSession(session);

        int userId = session.getUserId();

        locationService.addLocation(new LocationDto(name, userId, Double.parseDouble(latitude), Double.parseDouble(longitude)));

        return "redirect:/home";
    }
}
