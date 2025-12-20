package com.example.demo.repository;

import com.example.demo.entity.TransferRule;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface TransferRuleRepository extends JpaRepository<TransferRule, Long> {
    // Add this line to fix the "existsBySourceCourseIdAndTargetCourseId" error
    boolean existsBySourceCourseIdAndTargetCourseId(Long sourceId, Long targetId);
    
    Optional<TransferRule> findBySourceCourseIdAndTargetCourseId(Long sourceId, Long targetId);
}