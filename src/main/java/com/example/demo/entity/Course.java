package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "courses", uniqueConstraints = {@UniqueConstraint(columnNames = {"university_id", "courseCode"})})
@Data
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "university_id")
    private University university;

    private String courseCode;
    private String courseName;
    private Integer creditHours;
    private String description;
    private String department;

    @Column(nullable = false)
    private Boolean active = true;
}