package com.example.demo.service.impl;

import com.example.demo.dto.TransferEvaluationResponse;
import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.TransferEvaluationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransferEvaluationServiceImpl implements TransferEvaluationService {

    private final TransferEvaluationResultRepository resultRepo;
    private final CourseRepository courseRepo;
    private final CourseContentTopicRepository topicRepo;
    private final TransferRuleRepository ruleRepo;

    @Override
    public TransferEvaluationResponse evaluateTransfer(Long sourceId, Long targetId) {
        Course source = courseRepo.findById(sourceId).orElseThrow(() -> new RuntimeException("not found"));
        Course target = courseRepo.findById(targetId).orElseThrow(() -> new RuntimeException("not found"));

        if (!source.getActive() || !target.getActive()) {
            throw new RuntimeException("active");
        }

        var rules = ruleRepo.findBySourceUniversityIdAndTargetUniversityIdAndActiveTrue(
                source.getUniversity().getId(), target.getUniversity().getId());

        TransferEvaluationResponse response = new TransferEvaluationResponse();
        if (rules.isEmpty()) {
            response.setEligible(false);
            response.setRemarks("No active transfer rule");
            return response;
        }

        response.setEligible(true);
        response.setRemarks("Criteria met");
        
        TransferEvaluationResult res = new TransferEvaluationResult();
        res.setSourceCourse(source);
        res.setTargetCourse(target);
        res.setIsEligibleForTransfer(true);
        resultRepo.save(res);

        return response;
    }

    // FIXED: Added missing method from interface
    @Override
    public List<TransferEvaluationResult> getEvaluationsByCourseId(Long courseId) {
        return resultRepo.findBySourceCourseId(courseId);
    }

    @Override
    public TransferEvaluationResult getEvaluationById(Long id) {
        return resultRepo.findById(id).orElseThrow(() -> new RuntimeException("not found"));
    }
}