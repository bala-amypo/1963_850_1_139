package com.example.demo.service;

import com.example.demo.entity.TransferEvaluationResult;
import java.util.List;

public interface TransferValidationService {
    TransferEvaluationResult evaluateByCourseIds(Long sourceId, Long targetId);
    TransferEvaluationResult getEvaluationById(Long id);
    List<TransferEvaluationResult> getEvaluationsByCourse(Long courseId);
}