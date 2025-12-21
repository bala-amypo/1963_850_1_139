package com.example.demo.service;

import com.example.demo.dto.TransferEvaluationRequest;
import com.example.demo.dto.TransferEvaluationResponse;
import com.example.demo.entity.TransferEvaluationResult;
import java.util.List;

public interface TransferEvaluationService {
    TransferEvaluationResponse evaluateTransfer(TransferEvaluationRequest request);
    
    // Controller-la call panna extra methods
    TransferEvaluationResponse evaluateTransfer(Long sourceCourseId, Long targetCourseId);
    TransferEvaluationResult getEvaluationById(Long id);
    List<TransferEvaluationResult> getEvaluationsByCourseId(Long courseId);
}