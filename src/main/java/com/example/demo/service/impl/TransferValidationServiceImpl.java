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

    private final ProgramRepository programRepository;
    private final CourseRepository courseRepository;
    private final CourseMappingRepository mappingRepository;

    public TransferValidationServiceImpl(ProgramRepository programRepository, 
                                         CourseRepository courseRepository, 
                                         CourseMappingRepository mappingRepository) {
        this.programRepository = programRepository;
        this.courseRepository = courseRepository;
        this.mappingRepository = mappingRepository;
    }

    @Override
    public TransferEvaluationResponse evaluateTransfer(TransferEvaluationRequest request) {
        // 1. Validate Programs (Rule 6.6)
        programRepository.findById(request.getSourceProgramId())
            .orElseThrow(() -> new ResourceNotFoundException("Source program not found"));
        programRepository.findById(request.getTargetProgramId())
            .orElseThrow(() -> new ResourceNotFoundException("Target program not found"));

        double totalCredits = 0.0;
        List<String> accepted = new ArrayList<>();
        List<String> missing = new ArrayList<>();

        // 2. Logic: Resolve courses by code and apply mappings
        for (TransferEvaluationRequest.CompletedCourseDTO item : request.getCompletedCourses()) {
            Course sourceCourse = courseRepository.findByCodeIgnoreCase(item.getCode()).orElse(null);

            if (sourceCourse != null) {
                List<CourseMapping> mappings = mappingRepository.findBySourceCourseId(sourceCourse.getId());
                if (!mappings.isEmpty()) {
                    totalCredits += item.getCredits();
                    accepted.add("Accepted: " + item.getCode());
                } else {
                    missing.add("No mapping for: " + item.getCode());
                }
            } else {
                missing.add("Invalid code: " + item.getCode());
            }
        }

        // 3. Set result status (Rule 6.6)
        TransferEvaluationResponse response = new TransferEvaluationResponse();
        response.setTotalTransferableCredits(totalCredits);
        response.setAcceptedMappings(accepted);
        response.setMissingRequirements(missing);
        response.setStatus(totalCredits > 12.0 ? "APPROVED" : "PENDING"); // Example threshold
        response.setRemarks("Validation complete.");

        return response;
    }
}