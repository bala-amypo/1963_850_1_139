package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransferEvaluationResponse {
    private boolean isEligible;
    private String equivalentCourseName;
    private Integer transferCredits;
    private String remarks;
}