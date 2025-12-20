package com.example.demo.service.impl;

import com.example.demo.entity.TransferRule;
import com.example.demo.repository.TransferRuleRepository;
import com.example.demo.service.TransferValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransferValidationServiceImpl implements TransferValidationService {

    @Autowired
    private TransferRuleRepository transferRuleRepository;

    @Override
    public boolean validateTransfer(Long sourceCourseId, Long targetCourseId) {
        // We call the repository method and check if the rule exists and is active
        return transferRuleRepository.findBySourceCourse_IdAndTargetCourse_Id(sourceCourseId, targetCourseId)
                .map(rule -> rule.getActive() != null && rule.getActive())
                .orElse(false);
    }
}