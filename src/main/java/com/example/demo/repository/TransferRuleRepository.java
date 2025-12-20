package com.example.demo.repository;

import com.example.demo.entity.TransferRule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransferRuleRepository extends JpaRepository<TransferRule, Long> {
    // This MUST be here for the Service to compile
    boolean existsBySourceCourseIdAndTargetCourseId(Long sourceId, Long targetId);
}