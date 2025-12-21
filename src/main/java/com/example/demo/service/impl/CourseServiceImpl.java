package com.example.demo.service.impl;

import com.example.demo.entity.Course;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.exception.ValidationException;
import com.example.demo.repository.CourseRepository;
import com.example.demo.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    @Override
    public Course createCourse(Course course) {
        // Rule 6.4: credits must be > 0
        if (course.getCredits() == null || course.getCredits() <= 0) {
            throw new ValidationException("Credits must be greater than zero");
        }
        return courseRepository.save(course);
    }

    @Override
    public Course getCourseById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found"));
    }

    @Override
    public Course updateCourse(Long id, Course course) {
        Course existing = getCourseById(id);
        if (course.getCredits() <= 0) throw new ValidationException("Credits must be > 0");
        existing.setName(course.getName());
        existing.setCode(course.getCode());
        existing.setCredits(course.getCredits());
        return courseRepository.save(existing);
    }

    @Override
    public List<Course> getCoursesByUniversity(Long universityId) {
        // Logical lookup via repository
        return courseRepository.findAll(); // Simplify or add custom repo method if needed
    }

    @Override
    public void deactivateCourse(Long id) {
        courseRepository.deleteById(id);
    }
}