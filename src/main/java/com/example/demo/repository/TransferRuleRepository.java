package com.example.demo.repository;

import com.example.demo.entity.TransferRule;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface TransferRuleRepository extends JpaRepository<TransferRule, Long> {
    // This fixed the 'cannot find symbol' in your ServiceImpl
    Optional<TransferRule> findBySourceCourseIdAndTargetCourseId(Long sourceId, Long targetId);
}