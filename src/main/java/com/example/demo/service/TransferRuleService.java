package com.example.demo.service;

import com.example.demo.entity.TransferRule;
import java.util.List;

public interface TransferRuleService {
    TransferRule createRule(TransferRule rule);
    List<TransferRule> getAllRules();
    TransferRule getById(Long id);
    TransferRule updateRule(Long id, TransferRule updatedRule);
    TransferRule getRuleByPair(Long sourceId, Long targetId); // Matches your Impl code
    void deactivate(Long id);
}