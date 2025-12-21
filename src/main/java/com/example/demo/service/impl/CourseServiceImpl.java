package com.example.demo.service.impl;

import com.example.demo.entity.Course;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.UniversityRepository;
import com.example.demo.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    // STRICT NAMING: [Step 0.1]
    private final CourseRepository repo;
    private final UniversityRepository univRepo;

    @Override
    public Course createCourse(Course course) {
        // STRICT MESSAGE: [Step 0.3]
        if (course.getCreditHours() == null || course.getCreditHours() <= 0) {
            throw new RuntimeException("Credit hours must be > 0");
        }
        return repo.save(course);
    }

    @Override
    public Course getCourseById(Long id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("not found"));
    }

    @Override
    public List<Course> getCoursesByUniversity(Long universityId) {
        // FIXED: Using the required repository method
        return repo.findByUniversityIdAndActiveTrue(universityId);
    }

    @Override
    public void deactivateCourse(Long id) {
        Course course = getCourseById(id);
        course.setActive(false); // Make sure 'active' field exists in Course entity
        repo.save(course);
    }

    @Override
    public Course updateCourse(Long id, Course course) {
        Course existing = getCourseById(id);
        existing.setCourseName(course.getCourseName());
        existing.setCreditHours(course.getCreditHours());
        return repo.save(existing);
    }
}