package com.project.weatherApp.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="users")
public class User {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @Column(name="login")
    private String login;

    @Column(name="password")
    private String password;

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }
}
