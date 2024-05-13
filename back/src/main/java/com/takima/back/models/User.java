package com.takima.back.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "User")
public class User {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @Column(name="username")
    @Size(max = 40)
    private String username;

    @NotBlank
    @Column(name="email")
    @Size(max = 100)
    private String email;

    @NotBlank
    @Column(name="password")
    @Size(max = 100)
    private String password;

    // Getter and Setter methods
}


