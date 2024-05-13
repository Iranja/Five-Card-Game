//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.takima.back.controllers;

import com.takima.back.models.Course;
import com.takima.back.services.CourseService;
import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RequestMapping({"courses"})
@RestController
public class CourseController {
    private final CourseService courseService;

    @GetMapping({""})
    public List<Course> getAllCourses() {
        return this.courseService.findAll();
    }

    public CourseController(final CourseService courseService) {
        this.courseService = courseService;
    }
}
