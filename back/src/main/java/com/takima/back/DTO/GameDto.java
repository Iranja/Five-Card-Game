package com.takima.back.DTO;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class GameDto {
    private Long id;
    private String place;
    private Integer points;
    private LocalDate date;
    private LocalTime time;

    // Getters and setters
}
