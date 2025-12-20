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

    public boolean validateTransfer(Long sourceCourseId, Long targetCourseId) {
        // Updated to match the Repository method name
        return transferRuleRepository.findBySourceCourse_IdAndTargetCourse_Id(sourceCourseId, targetCourseId)
                .map(TransferRule::getActive)
                .orElse(false);
    }
}