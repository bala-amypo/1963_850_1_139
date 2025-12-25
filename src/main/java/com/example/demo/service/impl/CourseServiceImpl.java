package com.example.demo.service.impl;

import com.example.demo.entity.Course;
import com.example.demo.entity.University;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.UniversityRepository;
import com.example.demo.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service   // ⭐ MUST
public class CourseServiceImpl implements CourseService {

    // ⚠️ EXACT FIELD NAMES REQUIRED BY TESTS
    private CourseRepository repo;
    private UniversityRepository univRepo;

    // ✅ REQUIRED BY TEST CASES
    public CourseServiceImpl() {
    }

    // ✅ REQUIRED BY SPRING
    @Autowired
    public CourseServiceImpl(CourseRepository repo,
                             UniversityRepository univRepo) {
        this.repo = repo;
        this.univRepo = univRepo;
    }

    @Override
    public Course createCourse(Course course) {

        if (course.getCreditHours() == null || course.getCreditHours() <= 0) {
            throw new IllegalArgumentException("> 0");
        }

        Long univId = course.getUniversity().getId();
        University univ = univRepo.findById(univId)
                .orElseThrow(() -> new RuntimeException("not found"));

        if (repo.findByUniversityIdAndCourseCode(
                univId, course.getCourseCode()).isPresent()) {
            throw new IllegalArgumentException("exists");
        }

        course.setUniversity(univ);
        course.setActive(true);
        return repo.save(course);
    }

    @Override
    public Course updateCourse(Long id, Course course) {

        Course existing = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("not found"));

        if (course.getCreditHours() != null && course.getCreditHours() <= 0) {
            throw new IllegalArgumentException("> 0");
        }

        existing.setCourseName(course.getCourseName());
        existing.setCreditHours(course.getCreditHours());
        existing.setDescription(course.getDescription());
        existing.setDepartment(course.getDepartment());

        return repo.save(existing);
    }

    @Override
    public Course getCourseById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("not found"));
    }

    @Override
    public List<Course> getCoursesByUniversity(Long universityId) {
        return repo.findByUniversityIdAndActiveTrue(universityId);
    }

    @Override
    public void deactivateCourse(Long id) {

        Course course = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("not found"));

        course.setActive(false);
        repo.save(course);
    }
}
