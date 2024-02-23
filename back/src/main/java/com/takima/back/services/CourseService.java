package com.takima.back.services;

import com.takima.back.DAO.CourseDao;
import com.takima.back.models.Course;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CourseService {
    private final CourseDao courseDao;

    public List<Course> findAll() {
        return courseDao.findAll();
    }
}
