package com.project.weatherApp.controller;

import com.project.weatherApp.client.OpenWeatherApiClient;
import com.project.weatherApp.configuration.JpaConfig;
import com.project.weatherApp.configuration.SpringConfig;
import com.project.weatherApp.configuration.TestJpaConfig;
import com.project.weatherApp.exception.WeatherGeoNotFoundException;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.assertj.core.api.Assertions.assertThat;


@WebAppConfiguration
@ExtendWith({SpringExtension.class})
@ContextConfiguration(classes = {
        SpringConfig.class,
        JpaConfig.class,
        TestJpaConfig.class,
})
@Transactional
@ActiveProfiles("test")
public class OpenWeatherApiTest {
    @Autowired
    private OpenWeatherApiClient openWeatherApiClient;

    private static final String EXAMPLE_CITY = "London";
    private static final String EXAMPLE_UNCORRECT_CITY = "__---_";
    private static final String EXAMPLE_LAT = "51.50";
    private static final String EXAMPLE_LON = "-0.11";

    @Test
    void test_getWeatherForCity_geo() {
        assertThat(openWeatherApiClient.getLocationsByCityName(EXAMPLE_CITY).isEmpty()).isFalse();
    }

    @Test
    void test_getWeatherForLatAndLon_geo() {
        assertThat(openWeatherApiClient.getWeatherByLatAndLon(Double.parseDouble(EXAMPLE_LAT), Double.parseDouble(EXAMPLE_LON)).isPresent()).isTrue();
    }

    @Test
    void test_weatherGeoNotFoundException() {
        Assertions.assertThrows(WeatherGeoNotFoundException.class, () -> openWeatherApiClient.getLocationsByCityName(EXAMPLE_UNCORRECT_CITY));
    }
}
