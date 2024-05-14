package com.takima.back.DTO;

import com.takima.back.models.Gamer;

public class GamerMapper {
    public static Gamer fromDto(GamerDto dto) {
        return new Gamer.Builder()
                .id(dto.getId())
                .username(dto.getUsername())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .build();
    }

    public static GamerDto toDto(Gamer gamer) {
        return GamerDto.builder()
                .id(gamer.getId())
                .username(gamer.getUsername())
                .email(gamer.getEmail())
                .password(gamer.getPassword())
                .build();
    }
}
