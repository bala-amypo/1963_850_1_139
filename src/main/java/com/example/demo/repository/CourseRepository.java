package com.example.demo.repository;

import com.example.demo.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    // DOC REQUIREMENT: [Step 0.2]
    List<Course> findByUniversityIdAndActiveTrue(Long universityId);
    Optional<Course> findByUniversityIdAndCourseCode(Long universityId, String courseCode);
    
    // For general lookup
    Optional<Course> findByCourseCodeIgnoreCase(String courseCode);
}