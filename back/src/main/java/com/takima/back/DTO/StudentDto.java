package com.takima.back.DTO;

import com.takima.back.models.Course;
import com.takima.back.models.Major;
import lombok.Builder;
import lombok.Getter;

import java.time.Instant;
import java.util.List;
@Builder
@Getter
public class StudentDto {
    private String firstName;
    private String lastName;
    private Instant birthdate;
    private List<Course> courses;
    private Major major;
}
