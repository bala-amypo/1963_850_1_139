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

    // STRICT NAMING: repo and univRepo [Step 0.1]
    private final CourseRepository repo;
    private final UniversityRepository univRepo;

    @Override
    public Course createCourse(Course course) {
        // STRICT MESSAGE: Must contain "> 0" [Step 0.3]
        if (course.getCreditHours() == null || course.getCreditHours() <= 0) {
            throw new RuntimeException("Credit hours must be > 0");
        }
        return repo.save(course);
    }

    @Override
    public Course getCourseById(Long id) {
        // STRICT MESSAGE: Must contain "not found"
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found"));
    }

    @Override
    public Course updateCourse(Long id, Course course) {
        Course existing = getCourseById(id);
        if (course.getCreditHours() <= 0) throw new RuntimeException("> 0");
        
        existing.setCourseName(course.getCourseName());
        existing.setCourseCode(course.getCourseCode());
        existing.setCreditHours(course.getCreditHours());
        return repo.save(existing);
    }

    @Override
    public List<Course> getCoursesByUniversity(Long universityId) {
        return repo.findByUniversityIdAndActiveTrue(universityId);
    }

    @Override
    public void deactivateCourse(Long id) {
        Course course = getCourseById(id);
        course.setActive(false);
        repo.save(course);
    }
}