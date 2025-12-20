package com.example.demo.repository;

import com.example.demo.entity.TransferRule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface TransferRuleRepository extends JpaRepository<TransferRule, Long> {
    Optional<TransferRule> findBySourceCourse_IdAndTargetCourse_Id(Long sourceId, Long targetId);
}