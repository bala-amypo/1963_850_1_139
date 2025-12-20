package com.example.demo.service.impl;

import com.example.demo.dto.*;
import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.TransferValidationService;
import com.example.demo.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransferValidationServiceImpl implements TransferValidationService {

    private final UniversityRepository universityRepository;
    private final CourseRepository courseRepository;
    private final TransferRuleRepository transferRuleRepository;

    public TransferValidationServiceImpl(UniversityRepository universityRepository, 
                                         CourseRepository courseRepository, 
                                         TransferRuleRepository transferRuleRepository) {
        this.universityRepository = universityRepository;
        this.courseRepository = courseRepository;
        this.transferRuleRepository = transferRuleRepository;
    }

    @Override
    public TransferEvaluationResponse evaluateTransfer(TransferEvaluationRequest request) {
        // 1. Validate Universities exist (Rule 6.6)
        universityRepository.findById(request.getSourceProgramId()) // Using ID from DTO
            .orElseThrow(() -> new ResourceNotFoundException("Source university not found"));
        
        universityRepository.findById(request.getTargetProgramId())
            .orElseThrow(() -> new ResourceNotFoundException("Target university not found"));

        double totalCredits = 0.0;
        List<String> accepted = new ArrayList<>();

        // 2. Core Logic: Sum up credits for courses that exist in our system
        for (TransferEvaluationRequest.CompletedCourseDTO item : request.getCompletedCourses()) {
            // Find course by code
            Course course = courseRepository.findByUniversityIdAndCourseCode(request.getSourceProgramId(), item.getCode())
                .orElse(null);

            if (course != null) {
                totalCredits += item.getCredits();
                accepted.add("Matched: " + item.getCode());
            }
        }

        // 3. Prepare Final Response
        TransferEvaluationResponse response = new TransferEvaluationResponse();
        response.setTotalTransferableCredits(totalCredits);
        response.setAcceptedMappings(accepted);
        response.setStatus(totalCredits >= 15.0 ? "APPROVED" : "PENDING");
        response.setRemarks("Evaluation completed based on university rules.");

        return response;
    }
}