package com.example.demo.service.impl;

import com.example.demo.entity.CourseContentTopic;
import com.example.demo.repository.CourseContentTopicRepository;
import com.example.demo.repository.CourseRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CourseContentTopicServiceImpl {
    private CourseContentTopicRepository repo;
    private CourseRepository courseRepo;

    public CourseContentTopic createTopic(CourseContentTopic topic) {
        if (topic.getTopicName() == null || topic.getTopicName().trim().isEmpty()) {
            throw new IllegalArgumentException("Topic name required");
        }
        if (topic.getWeightPercentage() != null && (topic.getWeightPercentage() < 0 || topic.getWeightPercentage() > 100)) {
            throw new IllegalArgumentException("Weight percentage must be between 0-100");
        }
        if (courseRepo.findById(topic.getCourse().getId()).isEmpty()) {
            throw new RuntimeException("Course not found");
        }
        return repo.save(topic);
    }

    public CourseContentTopic updateTopic(Long id, CourseContentTopic topic) {
        CourseContentTopic existing = repo.findById(id)
            .orElseThrow(() -> new RuntimeException("Topic not found"));
        existing.setTopicName(topic.getTopicName());
        existing.setWeightPercentage(topic.getWeightPercentage());
        return repo.save(existing);
    }

    public CourseContentTopic getTopicById(Long id) {
        return repo.findById(id)
            .orElseThrow(() -> new RuntimeException("Topic not found"));
    }

    public List<CourseContentTopic> getTopicsForCourse(Long courseId) {
        if (courseRepo.findById(courseId).isEmpty()) {
            throw new RuntimeException("Course not found");
        }
        return repo.findByCourseId(courseId);
    }
}