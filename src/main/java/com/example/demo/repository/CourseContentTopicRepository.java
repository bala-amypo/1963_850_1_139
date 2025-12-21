package com.example.demo.repository;

import com.example.demo.entity.CourseContentTopic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CourseContentTopicRepository extends JpaRepository<CourseContentTopic, Long> {
    List<CourseContentTopic> findByCourseId(Long courseId);
}