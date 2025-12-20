package com.example.demo.service;

import com.example.demo.entity.TransferRule;
import java.util.List;

public interface TransferRuleService {
    TransferRule saveRule(TransferRule rule);
    List<TransferRule> getAllRules();
    void deleteRule(Long id);
}