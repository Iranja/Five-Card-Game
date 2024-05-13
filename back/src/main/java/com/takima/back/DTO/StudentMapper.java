//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.takima.back.DTO;

import com.takima.back.models.Student;
import java.io.IOException;

public class StudentMapper {
    public StudentMapper() {
    }

    public static Student fromDto(StudentDto dto, Long id) throws IOException {
        return (new Student.Builder()).id(id).firstName(dto.getFirstName()).lastName(dto.getLastName()).birthdate(dto.getBirthdate()).courses(dto.getCourses()).major(dto.getMajor()).build();
    }

    public static StudentDto toDto(Student student) {
        return StudentDto.builder().firstName(student.getFirstName()).lastName(student.getLastName()).birthdate(student.getBirthdate()).courses(student.getCourses()).major(student.getMajor()).build();
    }
}
