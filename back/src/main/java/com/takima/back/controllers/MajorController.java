//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.takima.back.controllers;

import com.takima.back.models.Major;
import com.takima.back.models.Student;
import com.takima.back.services.MajorService;
import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RequestMapping({"majors"})
@RestController
public class MajorController {
    private final MajorService majorService;

    @GetMapping({""})
    public List<Major> findAll() {
        return this.majorService.findAll();
    }

    @GetMapping({"/{id}/students"})
    public List<Student> getStudentsOfMajor(@PathVariable Long id) {
        return this.majorService.getStudentsOfMajor(id);
    }

    public MajorController(final MajorService majorService) {
        this.majorService = majorService;
    }
}
