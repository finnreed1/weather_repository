package com.project.weatherApp.controller;

import com.project.weatherApp.dto.LoginUserDto;
import com.project.weatherApp.service.AuthenticationService;
import com.project.weatherApp.service.SessionService;
import com.project.weatherApp.service.UserService;
import com.project.weatherApp.util.CookieUtils;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
@RequestMapping("/login")
public class SignInController {

    public final UserService userService;
    public final SessionService sessionService;
    public final AuthenticationService authService;
    public final CookieUtils cookieUtils;
    private static final Logger logger = LoggerFactory.getLogger(SignUpController.class);

    @GetMapping
    public String loginPage(Model model) {
        model.addAttribute("user", new LoginUserDto());
        return "sign-in";
    }

    @PostMapping
    public String login(@ModelAttribute("user") @Valid LoginUserDto user, BindingResult bindingResult, Model model, HttpServletResponse response) {
        if (bindingResult.hasErrors()) {
            logger.warn("validation errors : {}", bindingResult.getAllErrors());
            return "sign-in";
        }
        if (userService.isUniqueLogin(user.getLogin())){
            logger.warn("User {} is not found", user.getLogin());
            bindingResult.rejectValue("login", "user.error", "User is not found");
            return "sign-in";
        }
        if (!userService.isCorrectPassword(user)){
            logger.warn("Password {} is not correct", user.getPassword());
            bindingResult.rejectValue("password", "user.error", "Password is not correct");
            return "sign-in";
        }

        UUID sessionId = authService.login(user);
        Cookie cookie = cookieUtils.createCookie(sessionId);
        response.addCookie(cookie);

        return "redirect:/home";
    }

}