package com.example.demo.service;

import com.example.demo.entity.TransferEvaluationResult;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface TransferEvaluationService {

    TransferEvaluationResult evaluateTransfer(Long sourceCourseId, Long targetCourseId);

    TransferEvaluationResult getEvaluationById(Long id);

    List<TransferEvaluationResult> getEvaluationsForCourse(Long courseId);
}
