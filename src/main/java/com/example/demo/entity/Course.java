package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // Intha field thaan miss aayiruku!
    @Column(unique = true, nullable = false)
    private String code; 

    private Integer credits;

    @Column(length = 1000)
    private String description;

    @ManyToOne
    @JoinColumn(name = "program_id")
    private Program program;
}