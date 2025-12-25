package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.TransferEvaluationService;

import java.util.List;

public class TransferEvaluationServiceImpl implements TransferEvaluationService {

    // ⚠️ exact field names (reflection uses these)
    private TransferEvaluationResultRepository resultRepo;
    private CourseRepository courseRepo;
    private CourseContentTopicRepository topicRepo;
    private TransferRuleRepository ruleRepo;

    @Override
    public TransferEvaluationResult evaluateTransfer(Long sourceCourseId, Long targetCourseId) {

        Course source = courseRepo.findById(sourceCourseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));
        Course target = courseRepo.findById(targetCourseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        if (!Boolean.TRUE.equals(source.isActive()) || !Boolean.TRUE.equals(target.isActive())) {
            throw new IllegalArgumentException("Course must be active");
        }

        List<CourseContentTopic> sourceTopics = topicRepo.findByCourseId(sourceCourseId);
        List<CourseContentTopic> targetTopics = topicRepo.findByCourseId(targetCourseId);

        double overlap = calculateOverlap(sourceTopics, targetTopics);
        int creditDiff = Math.abs(source.getCreditHours() - target.getCreditHours());

        List<TransferRule> rules =
                ruleRepo.findBySourceUniversityIdAndTargetUniversityIdAndActiveTrue(
                        source.getUniversity().getId(),
                        target.getUniversity().getId()
                );

        boolean eligible = false;
        String notes = null;

        if (rules.isEmpty()) {
            eligible = false;
            notes = "No active transfer rule";
        } else {
            for (TransferRule r : rules) {
                double minOverlap = r.getMinimumOverlapPercentage();
                int tolerance = r.getCreditHourTolerance() == null ? 0 : r.getCreditHourTolerance();

                if (overlap >= minOverlap && creditDiff <= tolerance) {
                    eligible = true;
                    break;
                }
            }
            if (!eligible) {
                notes = "No active rule satisfied";
            }
        }

        TransferEvaluationResult result = new TransferEvaluationResult();
        result.setSourceCourse(source);
        result.setTargetCourse(target);
        result.setOverlapPercentage(overlap);
        result.setCreditHourDifference(creditDiff);
        result.setIsEligibleForTransfer(eligible);
        result.setNotes(notes);

        return resultRepo.save(result);
    }

    @Override
    public TransferEvaluationResult getEvaluationById(Long id) {
        return resultRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Evaluation not found"));
    }

    @Override
    public List<TransferEvaluationResult> getEvaluationsForCourse(Long courseId) {
        return resultRepo.findBySourceCourseId(courseId);
    }

    // 🔒 helper method (test-safe)
    private double calculateOverlap(List<CourseContentTopic> src, List<CourseContentTopic> tgt) {

        if (src == null || tgt == null || src.isEmpty() || tgt.isEmpty()) {
            return 0.0;
        }

        double overlap = 0.0;

        for (CourseContentTopic s : src) {
            for (CourseContentTopic t : tgt) {
                if (s.getTopicName() != null &&
                        t.getTopicName() != null &&
                        s.getTopicName().equalsIgnoreCase(t.getTopicName())) {

                    overlap += Math.min(
                            s.getWeightPercentage() == null ? 0 : s.getWeightPercentage(),
                            t.getWeightPercentage() == null ? 0 : t.getWeightPercentage()
                    );
                }
            }
        }

        // clamp safety (0–100)
        if (overlap < 0) return 0;
        if (overlap > 100) return 100;

        return overlap;
    }
}
