package com.example.demo.service;

import com.example.demo.entity.Course;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface CourseService {

    Course createCourse(Course course);

    Course updateCourse(Long id, Course course);

    Course getCourseById(Long id);

    List<Course> getCoursesByUniversity(Long universityId);

    void deactivateCourse(Long id);
}
