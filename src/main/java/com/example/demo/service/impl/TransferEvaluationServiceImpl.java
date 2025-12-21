package com.example.demo.service.impl;

import com.example.demo.dto.TransferEvaluationRequest;
import com.example.demo.dto.TransferEvaluationResponse;
import com.example.demo.entity.Course;
import com.example.demo.entity.TransferRule;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.TransferRuleRepository;
import com.example.demo.service.TransferEvaluationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransferEvaluationServiceImpl implements TransferEvaluationService {

    private final CourseRepository courseRepository;
    private final TransferRuleRepository ruleRepository;

    @Override
    public TransferEvaluationResponse evaluateTransfer(TransferEvaluationRequest request) {
        // Rule 6.6: Validate that courses/programs exist
        Course sourceCourse = courseRepository.findById(request.getCourseId())
                .orElseThrow(() -> new ResourceNotFoundException("Source Course not found"));

        // Step 4.5: Find active transfer rule for this mapping
        TransferRule rule = ruleRepository.findBySourceUniversityIdAndTargetUniversityId(
                request.getSourceUniversityId(), request.getTargetUniversityId())
                .stream().findFirst()
                .orElse(null);

        TransferEvaluationResponse response = new TransferEvaluationResponse();

        if (rule == null) {
            response.setEligible(false);
            response.setRemarks("No active transfer rule found for these universities.");
            return response;
        }

        // Logical logic for GPA and Credits (Dataset Step 4.5 & 6.6)
        if (request.getGpa() >= 3.0) { // Example threshold based on dataset context
            response.setEligible(true);
            response.setEquivalentCourseName(sourceCourse.getName());
            response.setTransferCredits(sourceCourse.getCredits());
            response.setRemarks("APPROVED: Criteria met.");
        } else {
            response.setEligible(false);
            response.setRemarks("REJECTED: GPA below requirement.");
        }

        return response;
    }
}

