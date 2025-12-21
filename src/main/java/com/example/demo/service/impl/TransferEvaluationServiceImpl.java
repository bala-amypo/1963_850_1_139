package com.example.demo.service.impl;

import com.example.demo.dto.*;
import com.example.demo.entity.TransferEvaluationResult;
import com.example.demo.service.TransferEvaluationService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TransferEvaluationServiceImpl implements TransferEvaluationService {

    @Override
    public TransferEvaluationResponse evaluateTransfer(TransferEvaluationRequest request) {
        // Your existing logic
        return new TransferEvaluationResponse();
    }

    @Override
    public TransferEvaluationResponse evaluateTransfer(Long sourceId, Long targetId) {
        TransferEvaluationRequest req = new TransferEvaluationRequest();
        req.setCourseId(sourceId); // Mapping path variables to DTO
        return evaluateTransfer(req);
    }

    @Override
    public TransferEvaluationResult getEvaluationById(Long id) {
        return null; // Add repository call here later
    }

    @Override
    public List<TransferEvaluationResult> getEvaluationsByCourseId(Long courseId) {
        return List.of(); // Add repository call here later
    }
}