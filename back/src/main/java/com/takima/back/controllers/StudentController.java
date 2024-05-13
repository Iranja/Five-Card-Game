//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.takima.back.controllers;

import com.takima.back.DTO.StudentDto;
import com.takima.back.models.Student;
import com.takima.back.services.StudentService;
import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RequestMapping({"students"})
@RestController
public class StudentController {
    private final StudentService studentService;

    @GetMapping({""})
    public List<Student> listStudents(@RequestParam(required = false) Integer majorId, @RequestParam(required = false) Integer courseId) {
        return majorId != null && courseId != null ? this.studentService.searchByMajorAndCourse(majorId, courseId) : this.studentService.findAll();
    }

    @GetMapping({"/{id}"})
    public Student getStudentById(@PathVariable Long id) {
        return this.studentService.getById(id);
    }

    @DeleteMapping({"/{id}"})
    public void deleteStudent(@PathVariable Long id) {
        this.studentService.deleteById(id);
    }

    @PostMapping({""})
    public void addStudent(@RequestBody StudentDto studentDto) {
        this.studentService.addStudent(studentDto);
    }

    @PostMapping({"/{id}"})
    public void updateStudent(@RequestBody StudentDto studentDto, @PathVariable Long id) {
        this.studentService.updateStudent(studentDto, id);
    }

    public StudentController(final StudentService studentService) {
        this.studentService = studentService;
    }
}
