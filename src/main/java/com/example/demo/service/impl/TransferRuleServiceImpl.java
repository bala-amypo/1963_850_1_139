package com.example.demo.service.impl;

import com.example.demo.entity.TransferRule;
import com.example.demo.repository.TransferRuleRepository;
import com.example.demo.repository.UniversityRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TransferRuleServiceImpl {
    private TransferRuleRepository repo;
    private UniversityRepository univRepo;

    public TransferRule createRule(TransferRule rule) {
        if (rule.getMinimumOverlapPercentage() != null && (rule.getMinimumOverlapPercentage() < 0 || rule.getMinimumOverlapPercentage() > 100)) {
            throw new IllegalArgumentException("Overlap percentage must be between 0-100");
        }
        if (rule.getCreditHourTolerance() != null && rule.getCreditHourTolerance() < 0) {
            throw new IllegalArgumentException("Credit hour tolerance must be >= 0");
        }
        if (rule.getSourceUniversity() != null && univRepo.findById(rule.getSourceUniversity().getId()).isEmpty()) {
            throw new RuntimeException("Source university not found");
        }
        if (rule.getTargetUniversity() != null && univRepo.findById(rule.getTargetUniversity().getId()).isEmpty()) {
            throw new RuntimeException("Target university not found");
        }
        return repo.save(rule);
    }

    public TransferRule updateRule(Long id, TransferRule rule) {
        TransferRule existing = repo.findById(id)
            .orElseThrow(() -> new RuntimeException("Rule not found"));
        existing.setMinimumOverlapPercentage(rule.getMinimumOverlapPercentage());
        existing.setCreditHourTolerance(rule.getCreditHourTolerance());
        return repo.save(existing);
    }

    public TransferRule getRuleById(Long id) {
        return repo.findById(id)
            .orElseThrow(() -> new RuntimeException("Rule not found"));
    }

    public List<TransferRule> getRulesForUniversities(Long sourceId, Long targetId) {
        return repo.findBySourceUniversityIdAndTargetUniversityIdAndActiveTrue(sourceId, targetId);
    }

    public void deactivateRule(Long id) {
        TransferRule rule = repo.findById(id)
            .orElseThrow(() -> new RuntimeException("Rule not found"));
        rule.setActive(false);
        repo.save(rule);
    }
}