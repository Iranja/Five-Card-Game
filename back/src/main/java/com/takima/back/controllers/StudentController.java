package com.takima.back.controllers;

import com.takima.back.DTO.StudentDto;
import com.takima.back.models.Student;
import com.takima.back.services.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RequestMapping("students")
@RestController
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;
    @GetMapping("")
    public List<Student> listStudents(@RequestParam(required = false) Integer majorId, @RequestParam(required = false) Integer courseId) {
        if (majorId != null && courseId !=null) {
            return studentService.searchByMajorAndCourse(majorId, courseId);
        }
        return studentService.findAll();
    }

    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable Long id) {
        return studentService.getById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable Long id) {
        studentService.deleteById(id);
    }

    @PostMapping("")
    public void addStudent(@RequestBody StudentDto studentDto) {
        studentService.addStudent(studentDto);
    }

    @PostMapping("/{id}")
    public void updateStudent(@RequestBody StudentDto studentDto, @PathVariable Long id) {
        studentService.updateStudent(studentDto, id);
    }
}
