//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.takima.back.services;

import com.takima.back.DAO.StudentDao;
import com.takima.back.DTO.StudentDto;
import com.takima.back.DTO.StudentMapper;
import com.takima.back.models.Student;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StudentService {
    private final StudentDao studentDao;

    public List<Student> findAll() {
        Iterable<Student> it = this.studentDao.findAll();
        List<Student> users = new ArrayList<>();
        Objects.requireNonNull(users);
        it.forEach(users::add);
        return users;
    }

    public Student getById(Long id) {
        return (Student)this.studentDao.findById(id).orElseThrow();
    }

    @Transactional
    public void deleteById(Long id) {
        this.studentDao.deleteById(id);
    }

    @Transactional
    public void addStudent(StudentDto studentDto) {
        Student student;
        try {
            student = StudentMapper.fromDto(studentDto, (Long)null);
        } catch (IOException var4) {
            throw new RuntimeException("Error with Student image", var4);
        }

        this.studentDao.save(student);
    }

    @Transactional
    public void updateStudent(StudentDto studentDto, Long id) {
        this.studentDao.findById(id).orElseThrow(() -> {
            return new NoSuchElementException("Student doesn't exist");
        });

        Student student;
        try {
            student = StudentMapper.fromDto(studentDto, id);
        } catch (IOException var5) {
            throw new RuntimeException("Error with Student image", var5);
        }

        this.studentDao.save(student);
    }

    public List<Student> searchByMajorAndCourse(int majorId, int courseId) {
        return this.studentDao.findByMajorIdAndCourseId(majorId, courseId);
    }

    public StudentService(final StudentDao studentDao) {
        this.studentDao = studentDao;
    }
}
