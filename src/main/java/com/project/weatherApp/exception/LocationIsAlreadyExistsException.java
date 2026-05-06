package com.project.weatherApp.exception;

public class LocationIsAlreadyExistsException extends RuntimeException {
    public LocationIsAlreadyExistsException(String message) {
        super(message);
    }
}
