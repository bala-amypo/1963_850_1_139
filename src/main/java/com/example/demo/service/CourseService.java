package com.example.demo.service;

import com.example.demo.entity.Course;
import java.util.List;

public interface CourseService {
    // Required for POST /
    Course createCourse(Course course);

    // Required for PUT /{id}
    Course updateCourse(Long id, Course course);

    // Required for GET /{id}
    Course getById(Long id);

    // Required for GET /university/{universityId}
    List<Course> getByUniversity(Long universityId);

    // Required for PUT /{id}/deactivate
    void deactivate(Long id);
}