package com.example.demo.service.impl;

import com.example.demo.entity.Course;
import com.example.demo.repository.CourseRepository;
import com.example.demo.service.CourseService;
import com.example.demo.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;

    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public Course createCourse(Course course) {
        // Rule 6.4: Strictly enforce positive credits
        if (course.getCredits() <= 0) {
            throw new IllegalArgumentException("credits must be > 0");
        }
        return courseRepository.save(course);
    }

    @Override
    public Course getByCode(String code) {
        return courseRepository.findByCodeIgnoreCase(code)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found"));
    }
}