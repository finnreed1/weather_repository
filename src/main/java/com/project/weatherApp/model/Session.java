package com.project.weatherApp.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Table(name="sessions")
public class Session {
    @Id
    @Column(name="id")
    private UUID id;

    @Column(name="userId")
    private int userId;

    @Column(name="expiresAt")
    private LocalDateTime expiresAt;
}
