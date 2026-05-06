package com.project.weatherApp.exception;

public class SearchLocationWithoutAuthenticationException extends RuntimeException {
    public SearchLocationWithoutAuthenticationException(String message) {
        super(message);
    }
}
