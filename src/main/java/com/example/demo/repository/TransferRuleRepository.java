package com.example.demo.repository;

import com.example.demo.entity.TransferRule;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface TransferRuleRepository extends JpaRepository<TransferRule, Long> {
    Optional<TransferRule> findBySourceUniversityIdAndTargetUniversityIdAndActiveTrue(Long sourceId, Long targetId);
}