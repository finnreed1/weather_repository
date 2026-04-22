package com.project.weatherApp.controller;

import com.project.weatherApp.dto.LoginUserDto;
import com.project.weatherApp.dto.RegistrationUserDto;
import com.project.weatherApp.exception.UserAuthenticationException;
import com.project.weatherApp.service.AuthenticationService;
import com.project.weatherApp.service.SessionService;
import com.project.weatherApp.service.UserService;
import jakarta.transaction.Transactional;
import org.h2.security.auth.AuthConfigException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;


@WebAppConfiguration
@ExtendWith({SpringExtension.class})
@ContextConfiguration(classes = {
        com.project.weatherApp.configuration.SpringConfig.class,
        com.project.weatherApp.configuration.JpaConfig.class,
        com.project.weatherApp.configuration.TestJpaConfig.class,
})
@Transactional
@ActiveProfiles("test")
class AuthenticationTest {
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private UserService userService;

    @Autowired
    private SessionService sessionService;

    @Test
    void test_registerUser(){
        RegistrationUserDto registrationUserDto = new RegistrationUserDto(LOGIN, PASSWORD, PASSWORD);
        userService.registrationUser(registrationUserDto);
        assertThat(userService.findUserByLogin(registrationUserDto.getLogin()).isPresent()).isTrue();
    }

    @Test
    void test_registerNotUniqueUser(){
        RegistrationUserDto registrationUserDto1 = new RegistrationUserDto(LOGIN, PASSWORD, PASSWORD);
        userService.registrationUser(registrationUserDto1);

        RegistrationUserDto registrationUserDto2 = new RegistrationUserDto(LOGIN, PASSWORD, PASSWORD);
        Assertions.assertThrows(UserAuthenticationException.class, () -> userService.registrationUser(registrationUserDto2));
    }

    @Test
    void test_authenticateUser(){
        RegistrationUserDto registrationUserDto = new RegistrationUserDto(LOGIN, PASSWORD, PASSWORD);
        userService.registrationUser(registrationUserDto);
        UUID loginId = authenticationService.login(new LoginUserDto(LOGIN, PASSWORD));
        int userId = userService.findUserByLogin(LOGIN).get().getId();
        Assertions.assertEquals(loginId, sessionService.findByUserId(userId).getId());
    }
}
