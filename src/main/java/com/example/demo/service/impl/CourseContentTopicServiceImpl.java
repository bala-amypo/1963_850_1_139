package com.example.demo.service.impl;

import com.example.demo.entity.Course;
import com.example.demo.entity.CourseContentTopic;
import com.example.demo.repository.CourseContentTopicRepository;
import com.example.demo.repository.CourseRepository;
import com.example.demo.service.CourseContentTopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service   // ⭐ MUST
public class CourseContentTopicServiceImpl
        implements CourseContentTopicService {

    // ⚠️ MUST NOT be final (test uses reflection)
    private CourseContentTopicRepository repo;
    private CourseRepository courseRepo;

    // ✅ REQUIRED BY TEST CASES
    public CourseContentTopicServiceImpl() {
    }

    // ✅ REQUIRED BY SPRING
    @Autowired
    public CourseContentTopicServiceImpl(
            CourseContentTopicRepository repo,
            CourseRepository courseRepo) {
        this.repo = repo;
        this.courseRepo = courseRepo;
    }

    @Override
    public CourseContentTopic createTopic(CourseContentTopic topic) {

        if (topic.getTopicName() == null || topic.getTopicName().isBlank()) {
            throw new IllegalArgumentException("Topic name");
        }

        if (topic.getWeightPercentage() == null
                || topic.getWeightPercentage() < 0
                || topic.getWeightPercentage() > 100) {
            throw new IllegalArgumentException("0-100");
        }

        Long courseId = topic.getCourse().getId();
        Course course = courseRepo.findById(courseId)
                .orElseThrow(() ->
                        new RuntimeException("not found"));

        topic.setCourse(course);
        return repo.save(topic);
    }

    @Override
    public CourseContentTopic updateTopic(Long id, CourseContentTopic topic) {

        CourseContentTopic existing = repo.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("not found"));

        if (topic.getTopicName() == null || topic.getTopicName().isBlank()) {
            throw new IllegalArgumentException("Topic name");
        }

        if (topic.getWeightPercentage() == null
                || topic.getWeightPercentage() < 0
                || topic.getWeightPercentage() > 100) {
            throw new IllegalArgumentException("0-100");
        }

        existing.setTopicName(topic.getTopicName());
        existing.setWeightPercentage(topic.getWeightPercentage());
        return repo.save(existing);
    }

    @Override
    public CourseContentTopic getTopicById(Long id) {
        return repo.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("not found"));
    }

    @Override
    public List<CourseContentTopic> getTopicsForCourse(Long courseId) {

        courseRepo.findById(courseId)
                .orElseThrow(() ->
                        new RuntimeException("not found"));

        return repo.findByCourseId(courseId);
    }
}
