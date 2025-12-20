package com.example.demo.controller;

import com.example.demo.entity.TransferRule;
import com.example.demo.service.TransferRuleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rules")
public class TransferRuleController {

    private final TransferRuleService transferRuleService;

    // Spring will now find the @Service implementation and inject it here
    public TransferRuleController(TransferRuleService transferRuleService) {
        this.transferRuleService = transferRuleService;
    }

    @PostMapping
    public ResponseEntity<TransferRule> createRule(@RequestBody TransferRule rule) {
        return ResponseEntity.ok(transferRuleService.saveRule(rule));
    }

    @GetMapping
    public ResponseEntity<List<TransferRule>> getAllRules() {
        return ResponseEntity.ok(transferRuleService.getAllRules());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRule(@PathVariable Long id) {
        transferRuleService.deleteRule(id);
        return ResponseEntity.noContent().build();
    }
}