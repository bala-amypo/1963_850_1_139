package com.example.demo.service.impl;

import com.example.demo.dto.TransferEvaluationResponse;
import com.example.demo.entity.Course;
import com.example.demo.entity.University;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.UniversityRepository;
import com.example.demo.repository.TransferRuleRepository;
import com.example.demo.service.TransferValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransferValidationServiceImpl implements TransferValidationService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private TransferRuleRepository transferRuleRepository;

    @Autowired
    private UniversityRepository universityRepository; // This line fixes your error!

    @Override
    public TransferEvaluationResponse evaluateByCourseIds(Long sourceId, Long targetId) {
        Course sourceCourse = courseRepository.findById(sourceId)
                .orElseThrow(() -> new RuntimeException("Source course not found"));
        Course targetCourse = courseRepository.findById(targetId)
                .orElseThrow(() -> new RuntimeException("Target course not found"));

        boolean isTransferable = transferRuleRepository.existsBySourceCourseIdAndTargetCourseId(sourceId, targetId);

        TransferEvaluationResponse response = new TransferEvaluationResponse();
        response.setIsTransferable(isTransferable);
        // Add other response mapping logic here as needed
        return response;
    }
}