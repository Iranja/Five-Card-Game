//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.takima.back.DTO;

import com.takima.back.models.Course;
import com.takima.back.models.Major;
import lombok.Getter;

import java.time.Instant;
import java.util.List;

@Getter
public class StudentDto {
    private String firstName;
    private String lastName;
    private Instant birthdate;
    private List<Course> courses;
    private Major major;

    StudentDto(final String firstName, final String lastName, final Instant birthdate, final List<Course> courses, final Major major) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.courses = courses;
        this.major = major;
    }

    public static StudentDtoBuilder builder() {
        return new StudentDtoBuilder();
    }

    public static class StudentDtoBuilder {
        private String firstName;
        private String lastName;
        private Instant birthdate;
        private List<Course> courses;
        private Major major;

        StudentDtoBuilder() {
        }

        public StudentDtoBuilder firstName(final String firstName) {
            this.firstName = firstName;
            return this;
        }

        public StudentDtoBuilder lastName(final String lastName) {
            this.lastName = lastName;
            return this;
        }

        public StudentDtoBuilder birthdate(final Instant birthdate) {
            this.birthdate = birthdate;
            return this;
        }

        public StudentDtoBuilder courses(final List<Course> courses) {
            this.courses = courses;
            return this;
        }

        public StudentDtoBuilder major(final Major major) {
            this.major = major;
            return this;
        }

        public StudentDto build() {
            return new StudentDto(this.firstName, this.lastName, this.birthdate, this.courses, this.major);
        }

        public String toString() {
            return "StudentDto.StudentDtoBuilder(firstName=" + this.firstName + ", lastName=" + this.lastName + ", birthdate=" + this.birthdate + ", courses=" + this.courses + ", major=" + this.major + ")";
        }
    }
}
