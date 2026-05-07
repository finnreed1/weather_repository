package com.project.weatherApp.service;

import com.project.weatherApp.client.OpenWeatherApiClient;
import com.project.weatherApp.dto.LocationDto;
import com.project.weatherApp.dto.WeatherResponseDto;
import com.project.weatherApp.exception.LocationIsAlreadyExistsException;
import com.project.weatherApp.mapper.LocationMapper;
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

    @Transactional
    public void addLocation(LocationDto locationDto) {
        if (locationRepository.findByCoordAndUserId(locationDto.getLatitude(), locationDto.getLongitude(), locationDto.getUserId()).isPresent()) {
            throw new LocationIsAlreadyExistsException("Location already exists");
        }
        locationRepository.save(LocationMapper.mapDtoToLocation(locationDto));
    }

    @Transactional
    public void deleteLocation(double locationLat, double locationLon, int userId) {
        Optional<Location> location = locationRepository.findByCoordAndUserId(locationLat, locationLon, userId);
        locationRepository.delete(location.get());
    }

    @Transactional
    public List<WeatherResponseDto> getWeatherByUserId(int userId) {
        List<Location> locations = locationRepository.findByUserId(userId);

        if (!locations.isEmpty()) {
            List<WeatherResponseDto> weatherResponseDtoList = new ArrayList<>();
            for (Location location : locations) {
                WeatherResponseDto weatherResponseDto = openWeatherApiClient.getWeatherByLatAndLon(location.getLatitude(), location.getLongitude()).get();
                weatherResponseDto.setName(location.getName());
                weatherResponseDtoList.add(weatherResponseDto);
            }
            return weatherResponseDtoList;
        } else {
            return List.of();
        }
    }
}