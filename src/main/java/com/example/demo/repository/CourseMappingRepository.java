package com.example.demo.repository;

import com.example.demo.entity.CourseMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CourseMappingRepository extends JpaRepository<CourseMapping, Long> {
    List<CourseMapping> findBySourceCourseId(Long id);
    List<CourseMapping> findByTargetCourseId(Long id);
}