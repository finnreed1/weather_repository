package com.project.weatherApp.validate;

public class PasswordValidator {
    public static boolean isPasswordsMatch(String password, String repeatPassword) {
        return password != null && password.equals(repeatPassword);
    }
}
