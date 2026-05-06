package com.project.weatherApp.controller;

import com.project.weatherApp.model.Session;
import com.project.weatherApp.service.LocationService;
import com.project.weatherApp.service.SessionService;
import com.project.weatherApp.util.ControllerUtils;
import com.project.weatherApp.util.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@Controller
public class HomePageController {

    @Autowired
    private ControllerUtils controllerUtils;

    @Autowired
    private SessionService sessionService;

    @Autowired
    private SessionUtils sessionUtil;

    @Autowired
    private LocationService locationService;

    @GetMapping("/home")
    public String homePage(@CookieValue(value ="MY_SESSION_ID", required = false, defaultValue = "") String sessionFromCookie, Model model) {
        if (sessionFromCookie == null || sessionFromCookie.isEmpty()) {
            controllerUtils.addEmptyAttributes(model);
            return "index";
        }
        Session session = sessionService.getSession(UUID.fromString(sessionFromCookie));

        sessionUtil.checkActiveSession(session);

        controllerUtils.addNonEmptyAttributes(model, session);

        return "index";
    }

    @PostMapping("/delete")
    public String deleteLocation(@CookieValue(value = "MY_SESSION_ID", required = false, defaultValue = "") String sessionFromCookie,
                                 @RequestParam("locationName") String locationName) {
        Session session = sessionService.getSession(UUID.fromString(sessionFromCookie));

        sessionUtil.checkActiveSession(session);

        int userId = session.getUserId();

        locationService.deleteLocation(locationName, userId);

        return "redirect:/home";
    }
}
