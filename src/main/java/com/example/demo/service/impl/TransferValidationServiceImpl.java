package com.example.demo.service.impl;

import com.example.demo.dto.TransferEvaluationResponse;
import com.example.demo.entity.Course;
import com.example.demo.entity.TransferRule;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.TransferRuleRepository;
import com.example.demo.service.TransferValidationService;
import com.example.demo.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransferValidationServiceImpl implements TransferValidationService {

    private final CourseRepository courseRepository;
    private final TransferRuleRepository transferRuleRepository;

    // Rule 6: Constructor Injection only
    public TransferValidationServiceImpl(CourseRepository courseRepository, 
                                         TransferRuleRepository transferRuleRepository) {
        this.courseRepository = courseRepository;
        this.transferRuleRepository = transferRuleRepository;
    }

    @Override
    public TransferEvaluationResponse evaluateByCourseIds(Long sourceCourseId, Long targetCourseId) {
        // 1. Fetch courses or throw "not found" (Rule 6.2/6.6)
        Course source = courseRepository.findById(sourceCourseId)
                .orElseThrow(() -> new ResourceNotFoundException("Source course not found"));
        Course target = courseRepository.findById(targetCourseId)
                .orElseThrow(() -> new ResourceNotFoundException("Target course not found"));

        // 2. Logic: Check if a transfer rule exists between these specific courses
        // This is where you'd check your mapping table
        boolean isTransferable = transferRuleRepository.existsBySourceCourseIdAndTargetCourseId(sourceCourseId, targetCourseId);

        // 3. Build Response
        TransferEvaluationResponse response = new TransferEvaluationResponse();
        response.setTotalTransferableCredits(isTransferable ? source.getCredits() : 0.0);
        response.setStatus(isTransferable ? "APPROVED" : "REJECTED");
        
        List<String> mappings = new ArrayList<>();
        if (isTransferable) {
            mappings.add(source.getCourseCode() + " -> " + target.getCourseCode());
        }
        response.setAcceptedMappings(mappings);
        response.setRemarks(isTransferable ? "Course matches institutional transfer rules." : "No valid mapping found.");

        return response;
    }

    @Override
    public TransferEvaluationResponse getEvaluationById(Long id) {
        // Implementation for retrieving a stored evaluation
        // (Requires a TransferEvaluationRepository)
        throw new UnsupportedOperationException("Fetch logic depends on your Evaluation Entity storage.");
    }

    @Override
    public List<TransferEvaluationResponse> getEvaluationsByCourse(Long courseId) {
        // Implementation for filtering evaluations by a specific course
        return new ArrayList<>(); 
    }
    @Override
public University updateUniversity(Long id, University universityDetails) {
    University university = universityRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("University not found with id: " + id));
    
    university.setName(universityDetails.getName());
    university.setLocation(universityDetails.getLocation());
    university.setActive(universityDetails.getActive());
    
    return universityRepository.save(university);
}
}