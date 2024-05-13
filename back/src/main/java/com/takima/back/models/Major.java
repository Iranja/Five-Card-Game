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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;

import java.util.List;

@Getter
@Entity
@Table(
        name = "majors"
)
public class Major {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    private String name;
    private String description;
    @OneToMany(
            mappedBy = "major"
    )
    @JsonIgnore
    private List<Student> students;

    public Major() {
    }
}
