package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "transfer_rules")
@Data
public class TransferRule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Direct IDs use pannuna error varathu (Strict Dataset Logic)
    private Long sourceUniversityId; 
    private Long targetUniversityId;

    private Double minGpa; // Controller and Service logic-ku ithu thaan venum
    private Double minimumOverlapPercentage;
    private Integer creditHourTolerance;
    private Boolean active = true;
}