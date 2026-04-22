package com.project.weatherApp.client;

import com.project.weatherApp.dto.WeatherResponseDto;
import com.project.weatherApp.exception.WeatherGeoNotFoundException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
@PropertySource("classpath:rest.properties")
@AllArgsConstructor
@NoArgsConstructor
public class OpenWeatherApiClient {
    @Value("${api.key}")
    private String apiKey;

    @Value("${url.city}")
    private String urlCity;

    @Value("${url.geo}")
    private String urlGeo;

    private final RestTemplate restTemplate = new RestTemplate();

    public List<WeatherResponseDto> getWeatherByCity(String city) {
        try {
            String url = String.format(urlCity, city, apiKey);
            return List.of(Objects.requireNonNull(restTemplate.getForObject(url, WeatherResponseDto.class)));
        } catch (HttpClientErrorException.NotFound e) {
            throw new WeatherGeoNotFoundException("City is not found");
        }
    }

    public Optional<WeatherResponseDto> getWeatherByLatAndLon(double lat, double lon) {
        try {
            String url = String.format(urlGeo, lat, lon, apiKey);
            return Optional.ofNullable(restTemplate.getForObject(url, WeatherResponseDto.class));
        } catch (HttpClientErrorException.NotFound e) {
            throw new WeatherGeoNotFoundException("City is not found");
        }
    }
}
