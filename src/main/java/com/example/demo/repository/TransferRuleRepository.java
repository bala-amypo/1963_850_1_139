package com.example.demo.repository;

import com.example.demo.entity.TransferRule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TransferRuleRepository extends JpaRepository<TransferRule, Long> {
    // DOC REQUIREMENT: Must have this exact name
    List<TransferRule> findBySourceUniversityIdAndTargetUniversityIdAndActiveTrue(Long sourceId, Long targetId);
    
    // Helper for general lookup
    List<TransferRule> findBySourceUniversityIdAndTargetUniversityId(Long sourceId, Long targetId);
}