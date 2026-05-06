package com.project.weatherApp.client;

import com.project.weatherApp.dto.GeocodingDto;
import com.project.weatherApp.dto.WeatherResponseDto;
import com.project.weatherApp.exception.WeatherGeoNotFoundException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.*;

@Component
@PropertySource("classpath:rest.properties")
@AllArgsConstructor
@NoArgsConstructor
public class OpenWeatherApiClient {
    @Value("${api.key}")
    private String API_KEY;

    @Value("${url.weather}")
    private String URL_WEATHER;

    @Value("${url.geo}")
    private String URL_GEOCODING;

    @Value("${url.units}")
    private String UNITS;

    @Value("${url.limit}")
    private int LIMIT;

    private final RestTemplate restTemplate = new RestTemplate();

    public List<GeocodingDto> getLocationsByCityName(String city) {
        try {
            String url = UriComponentsBuilder.fromUriString(URL_GEOCODING)
                    .queryParam("q", city)
                    .queryParam("appid", API_KEY)
                    .queryParam("limit", LIMIT)
                    .build()
                    .toUriString();
            GeocodingDto[] locations =  restTemplate.getForObject(url, GeocodingDto[].class);
            return Arrays.asList(Objects.requireNonNull(locations));
        } catch (HttpClientErrorException.NotFound e) {
            throw new WeatherGeoNotFoundException("Locations is not found");
        }
    }

    public Optional<WeatherResponseDto> getWeatherByLatAndLon(double lat, double lon) {
        try {
            String url = UriComponentsBuilder.fromUriString(URL_WEATHER)
                    .queryParam("lat", lat)
                    .queryParam("lon", lon)
                    .queryParam("appid", API_KEY)
                    .queryParam("units", UNITS)
                    .build()
                    .toUriString();
            return Optional.ofNullable(restTemplate.getForObject(url, WeatherResponseDto.class));
        } catch (HttpClientErrorException.NotFound e) {
            throw new WeatherGeoNotFoundException("City is not found");
        }
    }
}
