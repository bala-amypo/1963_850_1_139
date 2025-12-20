package com.example.demo.dto;

import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransferEvaluationRequest {
    private Long sourceProgramId;
    private Long targetProgramId;
    private List<CompletedCourseDTO> completedCourses;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CompletedCourseDTO {
        private String code;
        private String grade;
        private Double credits;
    }
}