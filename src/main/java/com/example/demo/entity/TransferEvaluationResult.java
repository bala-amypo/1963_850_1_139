package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import java.sql.Timestamp;

@Entity
@Table(name = "transfer_evaluation_results")
@Data
public class TransferEvaluationResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "source_course_id")
    private Course sourceCourse;

    @ManyToOne
    @JoinColumn(name = "target_course_id")
    private Course targetCourse;

    private Double overlapPercentage;
    private Integer creditHourDifference;
    private Boolean isEligibleForTransfer;

    @CreationTimestamp
    private Timestamp evaluatedAt; // Auto-generated

    private String notes; // Failure or Success reasons
}