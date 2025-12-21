package com.example.demo.controller;

import com.example.demo.entity.TransferRule;
import com.example.demo.service.TransferRuleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/transfer-rules")
@RequiredArgsConstructor
@Tag(name = "Transfer Rule")
public class TransferRuleController {

    private final TransferRuleService ruleService;

    @PostMapping("/")
    @Operation(summary = "Create a new transfer rule")
    public ResponseEntity<TransferRule> create(@RequestBody TransferRule rule) {
        return ResponseEntity.ok(ruleService.createRule(rule));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing rule")
    public ResponseEntity<TransferRule> update(@PathVariable Long id, @RequestBody TransferRule rule) {
        return ResponseEntity.ok(ruleService.updateRule(id, rule));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get rule by id")
    public ResponseEntity<TransferRule> getById(@PathVariable Long id) {
        return ResponseEntity.ok(ruleService.getRuleById(id));
    }

    @GetMapping("/pair/{sourceId}/{targetId}")
    @Operation(summary = "Get rules for a specific university pair")
    public ResponseEntity<List<TransferRule>> getByPair(@PathVariable Long sourceId, @PathVariable Long targetId) {
        return ResponseEntity.ok(ruleService.getRulesByUniversityPair(sourceId, targetId));
    }

    @PutMapping("/{id}/deactivate")
    @Operation(summary = "Deactivate a rule")
    public void deactivate(@PathVariable Long id) {
        ruleService.deactivateRule(id);
    }
}