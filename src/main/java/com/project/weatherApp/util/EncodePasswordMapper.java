package com.project.weatherApp.util;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class EncodePasswordMapper {
    public static String mapEncodePassword(String password) {
        String salt = BCrypt.gensalt(10);
        return BCrypt.hashpw(password, salt);
    }
}
