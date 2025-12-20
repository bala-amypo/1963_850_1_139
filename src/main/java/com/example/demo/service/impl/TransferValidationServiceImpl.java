package com.example.demo.service.impl;

import com.example.demo.entity.TransferEvaluationResult;
import com.example.demo.repository.TransferRuleRepository;
import com.example.demo.service.TransferValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;

@Service
public class TransferValidationServiceImpl implements TransferValidationService {

    @Autowired
    private TransferRuleRepository transferRuleRepository;

    @Override
    public TransferEvaluationResult evaluateByCourseIds(Long sourceId, Long targetId) {
        TransferEvaluationResult result = new TransferEvaluationResult();
        boolean exists = transferRuleRepository.findBySourceCourse_IdAndTargetCourse_Id(sourceId, targetId).isPresent();
        result.setIsEligibleForTransfer(exists);
        return result;
    }

    @Override
    public TransferEvaluationResult getEvaluationById(Long id) {
        return new TransferEvaluationResult(); 
    }

    @Override
    public List<TransferEvaluationResult> getEvaluationsByCourse(Long courseId) {
        return new ArrayList<>();
    }
}