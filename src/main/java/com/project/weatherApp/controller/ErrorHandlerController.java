package com.project.weatherApp.controller;

import com.project.weatherApp.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@ControllerAdvice
public class ErrorHandlerController {

    @ExceptionHandler(InvalidSessionException.class)
    public String handleInvalidSessionException(InvalidSessionException exception, Model model) {
        model.addAttribute("error", exception.getMessage());
        return "error";
    }

    @ExceptionHandler(UserAuthenticationException.class)
    public String handleUserAuthenticationException(UserAuthenticationException exception, Model model) {
        model.addAttribute("error", exception.getMessage());
        return "error";
    }

    @ExceptionHandler(WeatherGeoNotFoundException.class)
    public String handleWeatherGeoNotFoundException(WeatherGeoNotFoundException exception, Model model) {
        model.addAttribute("error", exception.getMessage());
        return "error";
    }

    @ExceptionHandler(LocationIsAlreadyExistsException.class)
    public String handleLocationIsAlreadyExistsException(LocationIsAlreadyExistsException exception, Model model) {
        model.addAttribute("error", exception.getMessage());
        return "error";
    }

    @ExceptionHandler(SearchLocationWithoutAuthenticationException.class)
    public String handleSearchLocationWithoutAuthenticationException(SearchLocationWithoutAuthenticationException exception,
                                                                     Model model) {
        model.addAttribute("error", exception.getMessage());
        return "error";
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleBadRequest(IllegalArgumentException e, Model model) {
        model.addAttribute("error", "Bad request exception");
        return "error";
    }

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNotFound(NoSuchElementException e, Model model) {
        model.addAttribute("error", "Resource not found");
        return "error";
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleGeneric(Exception e, Model model) {
        model.addAttribute("error", "Internal server error");
        model.addAttribute("timestamp", LocalDateTime.now());
        return "error";
    }
}
