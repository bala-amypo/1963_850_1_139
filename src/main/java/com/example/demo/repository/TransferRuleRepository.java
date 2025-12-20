package com.example.demo.repository;

import com.example.demo.entity.TransferRule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface TransferRuleRepository extends JpaRepository<TransferRule, Long> {
    // The underscore _Id tells Spring to look for the ID field inside the Course object
    Optional<TransferRule> findBySourceCourse_IdAndTargetCourse_Id(Long sourceCourseId, Long targetCourseId);
}