package com.project.weatherApp.service;

import com.project.weatherApp.client.OpenWeatherApiClient;
import com.project.weatherApp.dto.WeatherResponseDto;
import com.project.weatherApp.model.Location;
import com.project.weatherApp.repository.LocationRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LocationService {

    private final OpenWeatherApiClient openWeatherApiClient;
    private final LocationRepository locationRepository;

    public Optional findByCity(String city){
        return locationRepository.findByName(city);
    }

    public Optional findByLatAndLon(double lat, double lon){
        return locationRepository.findByLatAndLon(lat, lon);
    }

    @Transactional
    public void addLocation(Location location) {
        locationRepository.save(location);
    }

    @Transactional
    public void deleteLocation(String locationName, int userId) {
        Optional<Location> location = locationRepository.findByNameAndUserId(locationName, userId);
        locationRepository.delete(location.get());
    }

    @Transactional
    public List<WeatherResponseDto> getWeatherByUserId(int userId) {
        List<Location> locations = locationRepository.findByUserId(userId);
        if (!locations.isEmpty()) {
            List<WeatherResponseDto> weatherResponseDtoList = new ArrayList<>();
            for (Location location : locations) {
                weatherResponseDtoList.add(openWeatherApiClient.getWeatherByCity(location.getName()).get());
            }
            return weatherResponseDtoList;
        } else {
            return List.of();
        }
    }
}