package com.project.weatherApp.model;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Table(name="locations")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="name")
    private String name;

    @Column(name="userId")
    private int userId;

    @Column(name="latitude")
    private double latitude;

    @Column(name="longitude")
    private double longitude;
}