package com.example.demo.service.impl;

import com.example.demo.entity.Course;
import com.example.demo.entity.CourseContentTopic;
import com.example.demo.entity.TransferEvaluationResult;
import com.example.demo.entity.TransferRule;
import com.example.demo.repository.CourseContentTopicRepository;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.TransferEvaluationResultRepository;
import com.example.demo.repository.TransferRuleRepository;
import com.example.demo.service.TransferEvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service   // ⭐ MUST
public class TransferEvaluationServiceImpl
        implements TransferEvaluationService {

    // ⚠️ EXACT FIELD NAMES REQUIRED BY TESTS
    private TransferEvaluationResultRepository resultRepo;
    private CourseRepository courseRepo;
    private CourseContentTopicRepository topicRepo;
    private TransferRuleRepository ruleRepo;

    // ✅ REQUIRED BY TEST CASES
    public TransferEvaluationServiceImpl() {
    }

    // ✅ REQUIRED BY SPRING
    @Autowired
    public TransferEvaluationServiceImpl(
            TransferEvaluationResultRepository resultRepo,
            CourseRepository courseRepo,
            CourseContentTopicRepository topicRepo,
            TransferRuleRepository ruleRepo) {

        this.resultRepo = resultRepo;
        this.courseRepo = courseRepo;
        this.topicRepo = topicRepo;
        this.ruleRepo = ruleRepo;
    }

    @Override
    public TransferEvaluationResult evaluateTransfer(
            Long sourceCourseId,
            Long targetCourseId) {

        Course source = courseRepo.findById(sourceCourseId)
                .orElseThrow(() -> new RuntimeException("not found"));

        Course target = courseRepo.findById(targetCourseId)
                .orElseThrow(() -> new RuntimeException("not found"));

        if (!source.isActive() || !target.isActive()) {
            throw new IllegalArgumentException("active");
        }

        List<CourseContentTopic> sourceTopics =
                topicRepo.findByCourseId(sourceCourseId);
        List<CourseContentTopic> targetTopics =
                topicRepo.findByCourseId(targetCourseId);

        double overlap = 0.0;
        for (CourseContentTopic st : sourceTopics) {
            for (CourseContentTopic tt : targetTopics) {
                if (st.getTopicName()
                        .equalsIgnoreCase(tt.getTopicName())) {

                    overlap += Math.min(
                            st.getWeightPercentage(),
                            tt.getWeightPercentage());
                }
            }
        }

        int creditDiff =
                Math.abs(
                        source.getCreditHours()
                                - target.getCreditHours());

        List<TransferRule> rules =
                ruleRepo.findBySourceUniversityIdAndTargetUniversityIdAndActiveTrue(
                        source.getUniversity().getId(),
                        target.getUniversity().getId());

        boolean eligible = false;
        String notes;

        if (rules.isEmpty()) {
            notes = "No active transfer rule";
        } else {
            notes = "No active rule satisfied";
            for (TransferRule r : rules) {
                if (overlap >= r.getMinimumOverlapPercentage()
                        && creditDiff <= r.getCreditHourTolerance()) {

                    eligible = true;
                    notes = "Eligible";
                    break;
                }
            }
        }

        TransferEvaluationResult result =
                new TransferEvaluationResult();

        result.setSourceCourse(source);
        result.setTargetCourse(target);
        result.setOverlapPercentage(overlap);
        result.setCreditHourDifference(creditDiff);
        result.setIsEligibleForTransfer(eligible);
        result.setNotes(notes);
        // ✅ evaluatedAt AUTO-GENERATED in entity

        return resultRepo.save(result);
    }

    @Override
    public TransferEvaluationResult getEvaluationById(Long id) {
        return resultRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("not found"));
    }

    @Override
    public List<TransferEvaluationResult> getEvaluationsForCourse(Long courseId) {
        return resultRepo.findBySourceCourseId(courseId);
    }
}
