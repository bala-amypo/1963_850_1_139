package com.example.demo.service.impl;

import com.example.demo.dto.TransferEvaluationResponse;
import com.example.demo.entity.Course;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.TransferRuleRepository;
import com.example.demo.service.TransferValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Service
public class TransferValidationServiceImpl implements TransferValidationService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private TransferRuleRepository transferRuleRepository;

    @Override
    public TransferEvaluationResponse evaluateByCourseIds(Long sourceId, Long targetId) {
        TransferEvaluationResponse response = new TransferEvaluationResponse();
        boolean exists = transferRuleRepository.existsBySourceCourseIdAndTargetCourseId(sourceId, targetId);
        response.setIsTransferable(exists);
        response.setStatus(exists ? "APPROVED" : "DENIED");
        return response;
    }

    // This adds the missing method required by the Interface
    @Override
    public java.util.List<TransferEvaluationResponse> getEvaluationsByCourse(Long courseId) {
        // Return an empty list for now just to pass compilation
        return new ArrayList<>();
    }
}