package com.project.weatherApp.dto;

import jakarta.persistence.Column;
import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LocationDto {
    private String name;
    private int userId;
    private double latitude;
    private double longitude;
}
