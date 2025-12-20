package com.example.demo.repository;

import com.example.demo.entity.TransferRule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface TransferRuleRepository extends JpaRepository<TransferRule, Long> {
    // We use the underscore _Id to correctly map to the Course entity's ID
    Optional<TransferRule> findBySourceCourse_IdAndTargetCourse_Id(Long sourceCourseId, Long targetCourseId);
}