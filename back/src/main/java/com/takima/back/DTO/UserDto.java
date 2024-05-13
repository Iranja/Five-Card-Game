package com.takima.back.DTO;

import com.takima.back.models.Course;
import com.takima.back.models.Major;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class UserDto {
    private Long id;
    private String username;
    private String email;
    private String password;
}
