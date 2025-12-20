package com.example.demo.service;

import com.example.demo.entity.TransferRule;
import java.util.List;

public interface TransferRuleService {
    // Rule 6.5: Create a transfer mapping rule
    TransferRule createRule(TransferRule rule);
    
    // Find a rule based on the two universities involved
    TransferRule getRule(Long sourceId, Long targetId);
}