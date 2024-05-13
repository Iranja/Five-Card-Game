//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.takima.back.DAO;

import com.takima.back.models.Student;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentDao extends JpaRepository<Student, Long> {
    @Query("SELECT s FROM Student s JOIN s.courses c WHERE c.id= :courseId AND s.major.id = :majorId ")
    List<Student> findByMajorIdAndCourseId(int majorId, int courseId);
}
