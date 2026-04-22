package com.project.weatherApp.exception;

public class WeatherGeoNotFoundException  extends RuntimeException {
    public WeatherGeoNotFoundException(String message) {
        super(message);
    }
}
