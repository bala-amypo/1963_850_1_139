package com.example.demo.dto;

import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransferEvaluationResponse {
    private Double totalTransferableCredits;
    private List<String> acceptedMappings;
    private List<String> missingRequirements;
    private String status; // Usually "APPROVED", "REJECTED", or "PENDING"
    private String remarks;
}