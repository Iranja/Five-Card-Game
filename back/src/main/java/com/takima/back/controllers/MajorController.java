package com.takima.back.controllers;

import com.takima.back.models.Major;
import com.takima.back.models.Student;
import com.takima.back.services.MajorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RequestMapping("majors")
@RestController
@RequiredArgsConstructor
public class MajorController {
    private final MajorService majorService;

    @GetMapping("")
    public List<Major> findAll() {
        return majorService.findAll();
    }

    @GetMapping("/{id}/students")
    public List<Student> getStudentsOfMajor(@PathVariable Long id) {
        return majorService.getStudentsOfMajor(id);
    }
}
