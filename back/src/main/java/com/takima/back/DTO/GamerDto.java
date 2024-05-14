package com.takima.back.DTO;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class GamerDto {
    private Long id;
    private String username;
    private String email;
    private String password;
}
