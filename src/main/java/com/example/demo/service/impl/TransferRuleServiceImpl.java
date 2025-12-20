package com.example.demo.service.impl;

import com.example.demo.entity.TransferRule;
import com.example.demo.repository.TransferRuleRepository;
import com.example.demo.service.TransferRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TransferRuleServiceImpl implements TransferRuleService {

    @Autowired
    private TransferRuleRepository ruleRepository;

    @Override
    public TransferRule createRule(TransferRule rule) {
        return ruleRepository.save(rule);
    }

    @Override
    public List<TransferRule> getAllRules() {
        return ruleRepository.findAll();
    }

    @Override
    public TransferRule getById(Long id) {
        return ruleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rule not found with id: " + id));
    }

    @Override
    public TransferRule updateRule(Long id, TransferRule updatedRule) {
        TransferRule existingRule = getById(id);
        existingRule.setMinGradeRequired(updatedRule.getMinGradeRequired());
        existingRule.setTransferCredits(updatedRule.getTransferCredits());
        return ruleRepository.save(existingRule);
    }

    @Override
    public TransferRule getRuleByPair(Long sourceId, Long targetId) {
        return ruleRepository.findBySourceCourseIdAndTargetCourseId(sourceId, targetId)
                .orElseThrow(() -> new RuntimeException("No rule found for this course pair"));
    }

    @Override
    public void deactivate(Long id) {
        TransferRule rule = getById(id);
        rule.setActive(false);
        ruleRepository.save(rule);
    }
}