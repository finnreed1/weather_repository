package com.project.weatherApp.util;

import com.project.weatherApp.client.OpenWeatherApiClient;
import com.project.weatherApp.dto.GeocodingDto;
import com.project.weatherApp.dto.UserDto;
import com.project.weatherApp.dto.WeatherResponseDto;
import com.project.weatherApp.model.Session;
import com.project.weatherApp.service.LocationService;
import com.project.weatherApp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ControllerUtils {
    private final LocationService locationService;
    private final UserService userService;
    private final OpenWeatherApiClient openWeatherApiClient;

    public void addEmptyAttributes(Model model) {
        model.addAttribute("user", null);
        model.addAttribute("weatherDtoList", new ArrayList<>());
    }

    public void addNonEmptyAttributes(Model model, Session session) {
        UserDto user = userService.getUserDtoBySession(session);
        int userId = userService.getUserIdByLogin(user.getLogin());
        List<WeatherResponseDto> weatherResponseDtoList = locationService.getWeatherByUserId(userId);

        model.addAttribute("user", user);
        model.addAttribute("weatherDtoList", weatherResponseDtoList);
    }

    public void addLocationAttributes(Model model, String location, Session session) {
        UserDto user = userService.getUserDtoBySession(session);
        List<GeocodingDto> geocodingDtoList = openWeatherApiClient.getLocationsByCityName(location);

        model.addAttribute("user", user);
        model.addAttribute("locations", geocodingDtoList);
    }
}
