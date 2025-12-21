package com.example.demo.service.impl;

import com.example.demo.dto.TransferEvaluationRequest;
import com.example.demo.dto.TransferEvaluationResponse;
import com.example.demo.entity.Course;
import com.example.demo.entity.TransferRule;
import com.example.demo.entity.TransferEvaluationResult;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.TransferRuleRepository;
import com.example.demo.service.TransferEvaluationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransferEvaluationServiceImpl implements TransferEvaluationService {

    private final CourseRepository courseRepository;
    private final TransferRuleRepository ruleRepository;

    // Fix: Controller calls this with ID [Step 5.5]
    @Override
    public TransferEvaluationResponse evaluateTransfer(Long sourceCourseId, Long targetCourseId) {
        TransferEvaluationRequest request = new TransferEvaluationRequest();
        request.setCourseId(sourceCourseId);
        // Add target if needed in DTO, otherwise proceed with logic
        return evaluateTransfer(request);
    }

    @Override
    public TransferEvaluationResponse evaluateTransfer(TransferEvaluationRequest request) {
        Course sourceCourse = courseRepository.findById(request.getCourseId())
                .orElseThrow(() -> new ResourceNotFoundException("Source Course not found"));

        // Step 4.5: Find rule (Fixed with Entity field names)
        TransferRule rule = ruleRepository.findBySourceUniversityIdAndTargetUniversityId(
                request.getSourceUniversityId(), request.getTargetUniversityId())
                .stream().findFirst()
                .orElse(null);

        TransferEvaluationResponse response = new TransferEvaluationResponse();

        if (rule == null) {
            response.setEligible(false);
            response.setRemarks("No active transfer rule found.");
            return response;
        }

        if (request.getGpa() != null && request.getGpa() >= 3.0) {
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

    @Override
    public TransferEvaluationResult getEvaluationById(Long id) {
        // Mocking return for compilation; link to repository if created
        return new TransferEvaluationResult();
    }

    @Override
    public List<TransferEvaluationResult> getEvaluationsByCourseId(Long courseId) {
        // Mocking return for compilation
        return List.of();
    }
}