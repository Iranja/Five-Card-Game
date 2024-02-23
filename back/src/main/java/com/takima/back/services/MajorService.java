package com.takima.back.services;

import com.takima.back.DAO.MajorDao;
import com.takima.back.models.Major;
import com.takima.back.models.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class MajorService {
    private final MajorDao majorDao;
    public List<Major> findAll() {
        Iterable<Major> it = majorDao.findAll();
        List <Major> majors = new ArrayList<>();
        it.forEach(majors::add);
        return majors;
    }

    public List<Student> getStudentsOfMajor(Long id) {
        return majorDao.getAllStudentsFromMajor(id);
    }
}
