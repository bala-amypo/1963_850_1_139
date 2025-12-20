package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import java.sql.Timestamp;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "transfer_evaluation_results")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
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

    @CreationTimestamp // Auto-generated
    private Timestamp evaluatedAt;

    private String notes;
}