//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.takima.back.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;

import java.util.List;

@Getter
@Entity
@Table(
        name = "courses"
)
public class Course {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    private String name;
    private Integer hours;
    @ManyToMany(
            mappedBy = "courses"
    )
    @JsonIgnore
    List<Student> students;

    public Course() {
    }

}
