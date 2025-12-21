package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransferEvaluationRequest {
    // Service-la intha names thaan ethirpaakiraanga
    private Long sourceCourseId; 
    private Long targetCourseId;
    private Double gpa; // GPA logic evaluation-ku venum na vatchukonga
}