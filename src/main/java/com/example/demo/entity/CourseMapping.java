package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "course_mappings")
@Data
public class CourseMapping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "source_course_id")
    private Course sourceCourse;

    @ManyToOne
    @JoinColumn(name = "target_course_id")
    private Course targetCourse;

    private String equivalencyType;
    private String minGradeRequired;
}