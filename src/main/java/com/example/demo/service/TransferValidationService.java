package com.example.demo.service;

import com.example.demo.dto.TransferEvaluationResponse;
import java.util.List;

public interface TransferValidationService {
    // Ensure these names match what the Controller calls
    TransferEvaluationResponse evaluateByCourseIds(Long sourceCourseId, Long targetCourseId);
    TransferEvaluationResponse getEvaluationById(Long id);
    List<TransferEvaluationResponse> getEvaluationsByCourse(Long courseId);
}