package com.example.demo.service.impl;

import com.example.demo.entity.Course;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.UniversityRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CourseServiceImpl {
    private CourseRepository repo;
    private UniversityRepository univRepo;

    public Course createCourse(Course course) {
        if (course.getCreditHours() <= 0) {
            throw new IllegalArgumentException("Credit hours must be > 0");
        }
        if (univRepo.findById(course.getUniversity().getId()).isEmpty()) {
            throw new RuntimeException("University not found");
        }
        if (repo.findByUniversityIdAndCourseCode(course.getUniversity().getId(), course.getCourseCode()).isPresent()) {
            throw new IllegalArgumentException("Course code already exists");
        }
        return repo.save(course);
    }

    public Course updateCourse(Long id, Course course) {
        Course existing = repo.findById(id)
            .orElseThrow(() -> new RuntimeException("Course not found"));
        existing.setCourseName(course.getCourseName());
        existing.setCreditHours(course.getCreditHours());
        return repo.save(existing);
    }

    public Course getCourseById(Long id) {
        return repo.findById(id)
            .orElseThrow(() -> new RuntimeException("Course not found"));
    }

    public List<Course> getCoursesByUniversity(Long universityId) {
        return repo.findByUniversityIdAndActiveTrue(universityId);
    }

    public void deactivateCourse(Long id) {
        Course course = repo.findById(id)
            .orElseThrow(() -> new RuntimeException("Course not found"));
        course.setActive(false);
        repo.save(course);
    }
}