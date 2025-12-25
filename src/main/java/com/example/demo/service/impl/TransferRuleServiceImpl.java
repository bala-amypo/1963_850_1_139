package com.example.demo.service.impl;

import com.example.demo.entity.TransferRule;
import com.example.demo.entity.University;
import com.example.demo.repository.TransferRuleRepository;
import com.example.demo.repository.UniversityRepository;
import com.example.demo.service.TransferRuleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service   // ⭐ MUST
public class TransferRuleServiceImpl
        implements TransferRuleService {   // ⭐ MUST

    // ⚠️ Test cases expect exact field names
    private TransferRuleRepository repo;
    private UniversityRepository univRepo;

    public TransferRuleServiceImpl(TransferRuleRepository repo,
                                   UniversityRepository univRepo) {
        this.repo = repo;
        this.univRepo = univRepo;
    }

    @Override
    public TransferRule createRule(TransferRule rule) {

        if (rule.getMinimumOverlapPercentage() == null ||
                rule.getMinimumOverlapPercentage() < 0 ||
                rule.getMinimumOverlapPercentage() > 100) {
            throw new IllegalArgumentException("0-100");
        }

        if (rule.getCreditHourTolerance() != null &&
                rule.getCreditHourTolerance() < 0) {
            throw new IllegalArgumentException(">= 0");
        }

        Long sourceId = rule.getSourceUniversity().getId();
        Long targetId = rule.getTargetUniversity().getId();

        University source = univRepo.findById(sourceId)
                .orElseThrow(() -> new RuntimeException("not found"));
        University target = univRepo.findById(targetId)
                .orElseThrow(() -> new RuntimeException("not found"));

        rule.setSourceUniversity(source);
        rule.setTargetUniversity(target);
        rule.setActive(true);

        return repo.save(rule);
    }

    @Override
    public TransferRule updateRule(Long id, TransferRule rule) {
        TransferRule existing = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("not found"));

        if (rule.getMinimumOverlapPercentage() != null) {
            if (rule.getMinimumOverlapPercentage() < 0 ||
                    rule.getMinimumOverlapPercentage() > 100) {
                throw new IllegalArgumentException("0-100");
            }
            existing.setMinimumOverlapPercentage(
                    rule.getMinimumOverlapPercentage());
        }

        if (rule.getCreditHourTolerance() != null) {
            if (rule.getCreditHourTolerance() < 0) {
                throw new IllegalArgumentException(">= 0");
            }
            existing.setCreditHourTolerance(
                    rule.getCreditHourTolerance());
        }

        return repo.save(existing);
    }

    @Override
    public TransferRule getRuleById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("not found"));
    }

    @Override
    public List<TransferRule> getRulesForUniversities(Long sourceId, Long targetId) {
        return repo.findBySourceUniversityIdAndTargetUniversityIdAndActiveTrue(
                sourceId, targetId);
    }

    @Override
    public void deactivateRule(Long id) {
        TransferRule rule = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("not found"));
        rule.setActive(false);
        repo.save(rule);
    }
}
