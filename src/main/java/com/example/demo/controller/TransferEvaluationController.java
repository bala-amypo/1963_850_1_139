package com.example.demo.controller;

import com.example.demo.dto.TransferEvaluationResponse;
import com.example.demo.service.TransferValidationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/transfer-evaluations")
@Tag(name = "Transfer Evaluation Controller")
public class TransferEvaluationController {

    private final TransferValidationService validationService;

    public TransferEvaluationController(TransferValidationService validationService) {
        this.validationService = validationService;
    }

    @PostMapping("/evaluate/{sourceCourseId}/{targetCourseId}")
    public ResponseEntity<TransferEvaluationResponse> evaluate(
            @PathVariable Long sourceCourseId, 
            @PathVariable Long targetCourseId) {
        return ResponseEntity.ok(validationService.evaluateByCourseIds(sourceCourseId, targetCourseId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransferEvaluationResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(validationService.getEvaluationById(id));
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<TransferEvaluationResponse>> getByCourse(@PathVariable Long courseId) {
        return ResponseEntity.ok(validationService.getEvaluationsByCourse(courseId));
    }
}