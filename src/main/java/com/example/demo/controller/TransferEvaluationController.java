package com.example.demo.controller;

import com.example.demo.dto.TransferEvaluationRequest;
import com.example.demo.dto.TransferEvaluationResponse;
import com.example.demo.service.TransferEvaluationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transfer-evaluations")
@RequiredArgsConstructor
@Tag(name = "Transfer Evaluation")
public class TransferEvaluationController {
    private final TransferEvaluationService evaluationService;

    @PostMapping("/evaluate/{sourceCourseId}/{targetCourseId}")
    public ResponseEntity<TransferEvaluationResponse> evaluate(
            @PathVariable Long sourceCourseId, 
            @PathVariable Long targetCourseId) {
        // Logically map variables to request DTO for the service
        TransferEvaluationRequest req = new TransferEvaluationRequest();
        req.setCourseId(sourceCourseId);
        // Note: You might need to adjust logic here to match your TransferEvaluationRequest DTO
        return ResponseEntity.ok(evaluationService.evaluateTransfer(req));
    }
}