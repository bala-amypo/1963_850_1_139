package com.example.demo.service.impl;

import com.example.demo.repository.TransferRuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransferRuleServiceImpl {

    @Autowired
    private TransferRuleRepository ruleRepository;

    public void someMethod(Long sourceId, Long targetId) {
        // Updated to use the correct repository method name with underscore
        ruleRepository.findBySourceCourse_IdAndTargetCourse_Id(sourceId, targetId);
    }
}