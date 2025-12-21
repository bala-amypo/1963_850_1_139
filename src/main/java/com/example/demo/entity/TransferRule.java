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

    @ManyToOne
    @JoinColumn(name = "source_university_id")
    private University sourceUniversity;

    @ManyToOne
    @JoinColumn(name = "target_university_id")
    private University targetUniversity;

    private Double minimumOverlapPercentage;
    private Integer creditHourTolerance;
    private Boolean active = true;
}