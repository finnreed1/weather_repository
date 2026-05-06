package com.project.weatherApp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class GeocodingDto {
    private String name;
    @JsonProperty("local_names")
    private Map<String, String> localNames;
    private double lat;
    private double lon;
    private String country;
    private String state;

}
