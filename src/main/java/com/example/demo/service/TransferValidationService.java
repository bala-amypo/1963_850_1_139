package com.example.demo.service;

import com.example.demo.dto.TransferEvaluationRequest;
import com.example.demo.dto.TransferEvaluationResponse;

public interface TransferValidationService {
    // Rule 6.6: The main logic that calculates credit transfers
    TransferEvaluationResponse evaluateTransfer(TransferEvaluationRequest request);
}