package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "transfer_rules")
@Data
public class TransferRule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // These associations allow findBySourceCourseId to work automatically
    @ManyToOne
    @JoinColumn(name = "source_course_id")
    private Course sourceCourse;

    @ManyToOne
    @JoinColumn(name = "target_course_id")
    private Course targetCourse;

    private Double minimumOverlapPercentage;
    private Integer creditHourTolerance;
    private String minGradeRequired;
    private Double transferCredits;
    private Boolean active;
}