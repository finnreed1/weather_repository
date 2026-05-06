package com.project.weatherApp.controller;

import com.project.weatherApp.dto.RegistrationUserDto;
import com.project.weatherApp.service.UserService;
import com.project.weatherApp.validate.PasswordValidator;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
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

@Controller
@RequiredArgsConstructor
@RequestMapping("/registration")
public class SignUpController {

    public final UserService userService;

    public final PasswordValidator passwordValidator;

    private static final Logger logger = LoggerFactory.getLogger(SignUpController.class);

    @GetMapping
    public String signUpPage(Model model){
        model.addAttribute("user", new RegistrationUserDto());
        return "sign-up";
    }

    @PostMapping
    public String signUp(@ModelAttribute("user") @Valid RegistrationUserDto regUserDto,
                         BindingResult bindingResult, HttpServletResponse response) {
        if (bindingResult.hasErrors()) {
            logger.warn("validation errors : {}", bindingResult.getAllErrors());
            return "sign-up";
        }

        if (!passwordValidator.isPasswordsMatch(regUserDto.getPassword(), regUserDto.getRepeatPassword())){
            logger.info("Passwords is not match.");
            bindingResult.rejectValue("repeatPassword", "error.user", "Passwords is not match.");
            return "sign-up";
        }
        if (!userService.isUniqueLogin(regUserDto.getLogin())){
            logger.info("Username already exists.");
            bindingResult.rejectValue("login", "error.user", "Username already exists.");
            return "sign-up";
        }

        userService.registrationUser(regUserDto);

        return "redirect:/login";
    }
}
