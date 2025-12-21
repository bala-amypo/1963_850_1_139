package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransferEvaluationRequest {
    private Long sourceUniversityId;
    private Long targetUniversityId;
    private Long courseId;
    private Double gpa; // Extra info from supporting logic for credit transfer
}