package com.example.demo.controller;

import com.example.demo.entity.Course;
import com.example.demo.service.CourseService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/courses")
@Tag(name = "Course Controller")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping("/")
    public ResponseEntity<Course> create(@RequestBody Course c) {
        return ResponseEntity.ok(courseService.createCourse(c));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Course> update(@PathVariable Long id, @RequestBody Course c) {
        return ResponseEntity.ok(courseService.updateCourse(id, c));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> getById(@PathVariable Long id) {
        return ResponseEntity.ok(courseService.getById(id));
    }

    @GetMapping("/university/{universityId}")
    public ResponseEntity<List<Course>> getByUniversity(@PathVariable Long universityId) {
        return ResponseEntity.ok(courseService.getByUniversity(universityId));
    }

    @PutMapping("/{id}/deactivate")
    public ResponseEntity<Void> deactivate(@PathVariable Long id) {
        courseService.deactivate(id);
        return ResponseEntity.noContent().build();
    }
}