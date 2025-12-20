package com.example.demo.service.impl;

import com.example.demo.dto.TransferEvaluationResponse;
import com.example.demo.entity.Course;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.TransferRuleRepository;
import com.example.demo.service.TransferValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

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

    // This satisfies the missing method error you just saw
    @Override
    public TransferEvaluationResponse getEvaluationById(Long id) {
        // Returns an empty response for now to allow compilation
        return new TransferEvaluationResponse();
    }

    @Override
    public List<TransferEvaluationResponse> getEvaluationsByCourse(Long courseId) {
        return new ArrayList<>();
    }
}