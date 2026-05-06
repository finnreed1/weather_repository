package com.project.weatherApp.validate;

import org.springframework.stereotype.Component;

@Component
public class PasswordValidator {
    public boolean isPasswordsMatch(String password, String repeatPassword) {
        return password != null && password.equals(repeatPassword);
    }
}
