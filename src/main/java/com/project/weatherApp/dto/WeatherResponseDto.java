package com.project.weatherApp.dto;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class WeatherResponseDto {
    private Coordinate coord;
    private List<Weather> weather;
    private String base;
    private MainParam main;
    private int visibility;
    private Wind wind;
    private Cloud cloud;
    private int dt;
    private Sys sys;
    private int timezone;
    private int id;
    private String name;
    private int cod;

    @NoArgsConstructor
    @AllArgsConstructor
    @Setter
    @Getter
    @ToString
    private static class Coordinate implements Serializable {
        private double lat;
        private double lon;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    @ToString
    private static class Weather implements Serializable {
        private int id;
        private String main;
        private String description;
        private String icon;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    @ToString
    private static class MainParam implements Serializable {
        private double temp;
        private double feels_like;
        private double temp_max;
        private int pressure;
        private int humidity;
        private int sea_level;
        private int grnd_level;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    @ToString
    private static class Wind implements Serializable {
        private double speed;
        private int deg;
        private double gust;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    @ToString
    private static class Cloud implements Serializable {
        private int all;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    @ToString
    private static class Sys implements Serializable {
        private String country;
        private int sunrise;
        private int sunset;
    }
}

