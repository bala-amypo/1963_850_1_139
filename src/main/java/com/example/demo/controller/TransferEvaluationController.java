package com.example.demo.controller;

import com.example.demo.entity.TransferEvaluationResult;
import com.example.demo.service.TransferValidationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/evaluation")
public class TransferEvaluationController {

    private final TransferValidationService validationService;

    public TransferEvaluationController(TransferValidationService validationService) {
        this.validationService = validationService;
    }

    @PostMapping("/evaluate")
    public ResponseEntity<TransferEvaluationResult> evaluate(@RequestParam Long sourceId, @RequestParam Long targetId) {
        // Corrected: Returns TransferEvaluationResult to match Service
        return ResponseEntity.ok(validationService.evaluateByCourseIds(sourceId, targetId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransferEvaluationResult> getById(@PathVariable Long id) {
        // Corrected: Returns TransferEvaluationResult to match Service
        return ResponseEntity.ok(validationService.getEvaluationById(id));
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<TransferEvaluationResult>> getByCourse(@PathVariable Long courseId) {
        // Corrected: Returns List<TransferEvaluationResult> to match Service
        return ResponseEntity.ok(validationService.getEvaluationsByCourse(courseId));
    }
}