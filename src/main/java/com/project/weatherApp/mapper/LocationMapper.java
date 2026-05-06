package com.project.weatherApp.mapper;

import com.project.weatherApp.dto.LocationDto;
import com.project.weatherApp.dto.WeatherResponseDto;
import com.project.weatherApp.model.Location;

public class LocationMapper {
    public static Location mapDtoToLocation(LocationDto locationDto) {
        return Location.builder()
                .name(locationDto.getName())
                .latitude(locationDto.getLatitude())
                .longitude(locationDto.getLongitude())
                .userId(locationDto.getUserId())
                .build();
    }
}
