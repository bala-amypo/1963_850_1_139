package com.example.demo.service;

import com.example.demo.entity.TransferRule;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface TransferRuleService {

    TransferRule createRule(TransferRule rule);

    TransferRule updateRule(Long id, TransferRule rule);

    TransferRule getRuleById(Long id);

    List<TransferRule> getRulesForUniversities(Long sourceId, Long targetId);

    void deactivateRule(Long id);
}
