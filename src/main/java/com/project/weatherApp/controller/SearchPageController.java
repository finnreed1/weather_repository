package com.project.weatherApp.controller;

import com.project.weatherApp.model.Session;
import com.project.weatherApp.service.SessionService;
import com.project.weatherApp.util.SessionUtil;
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
    private SessionUtil sessionUtil;

    @GetMapping("/search")
    public String search(@CookieValue(value="MY_SESSION_ID", required = false, defaultValue = "") String sessionFromCookie,
                         @RequestParam("name") String name,
                         Model model){
        Session session = sessionService.getSession(UUID.fromString(sessionFromCookie));

        sessionUtil.checkActiveSession(session);




    }

    @PostMapping("/add")
    public String addLocation(){


    }
}
