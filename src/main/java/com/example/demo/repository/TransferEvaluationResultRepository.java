package com.example.demo.repository;

import com.example.demo.entity.TransferEvaluationResult;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransferEvaluationResultRepository extends JpaRepository<TransferEvaluationResult, Long> {
    // Basic CRUD is enough as per dataset
}