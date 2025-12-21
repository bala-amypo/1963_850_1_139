package com.example.demo.controller;

import com.example.demo.dto.TransferEvaluationResponse;
import com.example.demo.entity.TransferEvaluationResult;
import com.example.demo.service.TransferEvaluationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/transfer-evaluations")
@RequiredArgsConstructor
@Tag(name = "Transfer Evaluation")
public class TransferEvaluationController {

    private final TransferEvaluationService evaluationService;

    @PostMapping("/evaluate/{sourceCourseId}/{targetCourseId}")
    @Operation(summary = "Evaluate course transfer eligibility")
    public ResponseEntity<TransferEvaluationResponse> evaluate(
            @PathVariable Long sourceCourseId, 
            @PathVariable Long targetCourseId) {
        return ResponseEntity.ok(evaluationService.evaluateTransfer(sourceCourseId, targetCourseId));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get evaluation result by id")
    public ResponseEntity<TransferEvaluationResult> getById(@PathVariable Long id) {
        return ResponseEntity.ok(evaluationService.getEvaluationById(id));
    }

    @GetMapping("/course/{courseId}")
    @Operation(summary = "Get previous evaluations for a course")
    public ResponseEntity<List<TransferEvaluationResult>> getByCourse(@PathVariable Long courseId) {
        return ResponseEntity.ok(evaluationService.getEvaluationsByCourseId(courseId));
    }
}