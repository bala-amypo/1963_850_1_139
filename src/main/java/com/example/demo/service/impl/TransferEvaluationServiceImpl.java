package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TransferEvaluationServiceImpl {
    private CourseRepository courseRepo;
    private CourseContentTopicRepository topicRepo;
    private TransferRuleRepository ruleRepo;
    private TransferEvaluationResultRepository resultRepo;

    public TransferEvaluationResult evaluateTransfer(Long sourceCourseId, Long targetCourseId) {
        Course sourceCourse = courseRepo.findById(sourceCourseId)
            .orElseThrow(() -> new RuntimeException("Source course not found"));
        Course targetCourse = courseRepo.findById(targetCourseId)
            .orElseThrow(() -> new RuntimeException("Target course not found"));

        if (!sourceCourse.isActive() || !targetCourse.isActive()) {
            throw new IllegalArgumentException("Both courses must be active");
        }

        List<CourseContentTopic> sourceTopics = topicRepo.findByCourseId(sourceCourseId);
        List<CourseContentTopic> targetTopics = topicRepo.findByCourseId(targetCourseId);

        double overlapPercentage = calculateOverlap(sourceTopics, targetTopics);

        List<TransferRule> rules = ruleRepo.findBySourceUniversityIdAndTargetUniversityIdAndActiveTrue(
            sourceCourse.getUniversity().getId(), targetCourse.getUniversity().getId());

        TransferEvaluationResult result = new TransferEvaluationResult();
        result.setSourceCourse(sourceCourse);
        result.setTargetCourse(targetCourse);
        result.setOverlapPercentage(overlapPercentage);

        if (rules.isEmpty()) {
            result.setIsEligibleForTransfer(false);
            result.setNotes("No active transfer rule found between universities");
        } else {
            boolean eligible = false;
            for (TransferRule rule : rules) {
                if (overlapPercentage >= rule.getMinimumOverlapPercentage()) {
                    int creditDiff = Math.abs(sourceCourse.getCreditHours() - targetCourse.getCreditHours());
                    if (creditDiff <= rule.getCreditHourTolerance()) {
                        eligible = true;
                        break;
                    }
                }
            }
            result.setIsEligibleForTransfer(eligible);
            result.setNotes(eligible ? "Transfer approved" : "No active rule satisfied all criteria");
        }

        return resultRepo.save(result);
    }

    private double calculateOverlap(List<CourseContentTopic> sourceTopics, List<CourseContentTopic> targetTopics) {
        if (sourceTopics.isEmpty() && targetTopics.isEmpty()) return 100.0;
        if (sourceTopics.isEmpty() || targetTopics.isEmpty()) return 0.0;

        double totalSourceWeight = sourceTopics.stream().mapToDouble(t -> t.getWeightPercentage() != null ? t.getWeightPercentage() : 0).sum();
        if (totalSourceWeight == 0) totalSourceWeight = 100.0;

        double matchedWeight = 0.0;
        for (CourseContentTopic sourceTopic : sourceTopics) {
            for (CourseContentTopic targetTopic : targetTopics) {
                if (sourceTopic.getTopicName().equalsIgnoreCase(targetTopic.getTopicName())) {
                    double sourceWeight = sourceTopic.getWeightPercentage() != null ? sourceTopic.getWeightPercentage() : 0;
                    double targetWeight = targetTopic.getWeightPercentage() != null ? targetTopic.getWeightPercentage() : 0;
                    matchedWeight += Math.min(sourceWeight, targetWeight);
                    break;
                }
            }
        }

        return (matchedWeight / totalSourceWeight) * 100.0;
    }

    public TransferEvaluationResult getEvaluationById(Long id) {
        return resultRepo.findById(id)
            .orElseThrow(() -> new RuntimeException("Evaluation not found"));
    }

    public List<TransferEvaluationResult> getEvaluationsForCourse(Long sourceCourseId) {
        return resultRepo.findBySourceCourseId(sourceCourseId);
    }
}