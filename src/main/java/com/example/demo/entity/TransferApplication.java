package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "transfer_applications")
@Data
public class TransferApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Oru user nariya applications poodalam
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User student;

    // Oru application oru university-ku thaan poga mudiyum
    @ManyToOne
    @JoinColumn(name = "university_id", nullable = false)
    private University targetUniversity;

    private String status; // PENDING, APPROVED, REJECTED
    private String currentCourse;
    private Double currentGpa;
    private LocalDateTime appliedAt = LocalDateTime.now();
}