package com.example.demo.service.impl;

import com.example.demo.entity.Course;
import com.example.demo.repository.CourseRepository;
import com.example.demo.service.CourseService;
import com.example.demo.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    // Rule 6: Constructor Injection
    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public Course createCourse(Course course) {
        // Rule 6.4: Credits must be > 0
        if (course.getCredits() <= 0) {
            throw new IllegalArgumentException("credits must be > 0");
        }
        return courseRepository.save(course);
    }

    @Override
    public Course updateCourse(Long id, Course courseDetails) {
        Course course = getById(id);
        course.setCourseName(courseDetails.getCourseName());
        course.setCourseCode(courseDetails.getCourseCode());
        course.setCredits(courseDetails.getCredits());
        return courseRepository.save(course);
    }

    @Override
    public Course getById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + id));
    }

    @Override
    public List<Course> getByUniversity(Long universityId) {
        return courseRepository.findByUniversityId(universityId);
    }

    @Override
    public void deactivate(Long id) {
        Course course = getById(id);
        course.setActive(false); // Ensure 'active' field exists in your Course entity
        courseRepository.save(course);
    }
}