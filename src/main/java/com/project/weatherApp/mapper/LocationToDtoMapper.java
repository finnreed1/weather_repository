package com.project.weatherApp.mapper;

import com.project.weatherApp.dto.LocationDto;
import com.project.weatherApp.model.Location;

public class LocationToDtoMapper {
    public static LocationDto map(Location location) {
        LocationDto locationDto = new LocationDto();
        locationDto.setName(location.getName());
        locationDto.setLatitude(location.getLatitude());
        locationDto.setLongitude(location.getLongitude());
        locationDto.setUserId(location.getUserId());
        return locationDto;
    }
}
