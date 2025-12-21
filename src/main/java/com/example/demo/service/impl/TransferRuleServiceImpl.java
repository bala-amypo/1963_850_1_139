package com.example.demo.service.impl;

import com.example.demo.entity.TransferRule;
import com.example.demo.repository.TransferRuleRepository;
import com.example.demo.service.TransferRuleService;
import com.example.demo.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransferRuleServiceImpl implements TransferRuleService {

    private final TransferRuleRepository ruleRepository;

    @Override
    public TransferRule createRule(TransferRule rule) {
        rule.setActive(true);
        return ruleRepository.save(rule);
    }

    @Override
    public TransferRule updateRule(Long id, TransferRule ruleDetails) {
        TransferRule rule = ruleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rule not found with id: " + id));
        rule.setSourceUniversityId(ruleDetails.getSourceUniversityId());
        rule.setTargetUniversityId(ruleDetails.getTargetUniversityId());
        rule.setMinGpa(ruleDetails.getMinGpa());
        return ruleRepository.save(rule);
    }

    @Override
    public TransferRule getRuleById(Long id) {
        return ruleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rule not found with id: " + id));
    }

    @Override
    public List<TransferRule> getRulesByUniversityPair(Long sourceId, Long targetId) {
        return ruleRepository.findBySourceUniversityIdAndTargetUniversityId(sourceId, targetId);
    }

    @Override
    public void deactivateRule(Long id) {
        TransferRule rule = getRuleById(id);
        rule.setActive(false);
        ruleRepository.save(rule);
    }
}