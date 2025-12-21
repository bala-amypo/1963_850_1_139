package com.example.demo.service;

import com.example.demo.dto.TransferEvaluationRequest;
import com.example.demo.dto.TransferEvaluationResponse;

public interface TransferEvaluationService {
    TransferEvaluationResponse evaluateTransfer(TransferEvaluationRequest request);
}