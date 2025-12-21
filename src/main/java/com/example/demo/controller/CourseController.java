package com.example.demo.controller;

import com.example.demo.entity.Course;
import com.example.demo.service.CourseService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
@Tag(name = "Course")
public class CourseController {
    private final CourseService courseService;

    @PostMapping("/")
    public ResponseEntity<Course> create(@RequestBody Course course) {
        return ResponseEntity.ok(courseService.createCourse(course));
    }

    @GetMapping("/university/{universityId}")
    public ResponseEntity<List<Course>> getByUniv(@PathVariable Long universityId) {
        return ResponseEntity.ok(courseService.getCoursesByUniversity(universityId));
    }
    
    @PutMapping("/{id}/deactivate")
    public void deactivate(@PathVariable Long id) {
        courseService.deactivateCourse(id);
    }
}