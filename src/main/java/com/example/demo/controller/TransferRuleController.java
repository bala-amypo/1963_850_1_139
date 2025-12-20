package com.example.demo.controller;

import com.example.demo.entity.TransferRule;
import com.example.demo.service.TransferRuleService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transfer-rules")
@Tag(name = "Transfer Rule Controller")
public class TransferRuleController {

    private final TransferRuleService ruleService;

    public TransferRuleController(TransferRuleService ruleService) {
        this.ruleService = ruleService;
    }

    @PostMapping("/")
    public ResponseEntity<TransferRule> create(@RequestBody TransferRule r) {
        return ResponseEntity.ok(ruleService.createRule(r));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TransferRule> update(@PathVariable Long id, @RequestBody TransferRule r) {
        return ResponseEntity.ok(ruleService.updateRule(id, r));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransferRule> getById(@PathVariable Long id) {
        return ResponseEntity.ok(ruleService.getById(id));
    }

    @GetMapping("/pair/{sourceId}/{targetId}")
    public ResponseEntity<TransferRule> getByPair(@PathVariable Long sourceId, @PathVariable Long targetId) {
        return ResponseEntity.ok(ruleService.getRuleByPair(sourceId, targetId));
    }

    @PutMapping("/{id}/deactivate")
    public ResponseEntity<Void> deactivate(@PathVariable Long id) {
        ruleService.deactivate(id);
        return ResponseEntity.noContent().build();
    }
}