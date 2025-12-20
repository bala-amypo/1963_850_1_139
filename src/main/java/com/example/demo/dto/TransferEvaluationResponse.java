package com.example.demo.dto;

import lombok.Data;
import java.util.List;

@Data
public class TransferEvaluationResponse {
    private boolean transferable; // Changed from 'isTransferable' to ensure 'setTransferable'
    private Double totalCredits;
    private String status;
    private List<String> details;
    
    // Manual setter to be safe against the compiler error
    public void setIsTransferable(boolean transferable) {
        this.transferable = transferable;
    }
}