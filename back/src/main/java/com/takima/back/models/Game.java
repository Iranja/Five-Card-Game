package com.takima.back.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "game")
@Getter
@Setter
public class Game {

    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Gamer user;

    private String place;
    private Integer points;
    private LocalDate date;
    private LocalTime time;


    // Getters and setters
}
