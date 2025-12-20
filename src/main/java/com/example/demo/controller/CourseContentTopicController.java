package com.example.demo.controller;

import com.example.demo.entity.CourseContentTopic; // Ensure entity name matches
import com.example.demo.service.CourseContentTopicService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/topics")
@Tag(name = "Course Content Topic Controller")
public class CourseContentTopicController {

    private final CourseContentTopicService topicService;

    public CourseContentTopicController(CourseContentTopicService topicService) {
        this.topicService = topicService;
    }

    @PostMapping("/")
    public ResponseEntity<CourseContentTopic> create(@RequestBody CourseContentTopic t) {
        return ResponseEntity.ok(topicService.createTopic(t));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseContentTopic> update(@PathVariable Long id, @RequestBody CourseContentTopic t) {
        return ResponseEntity.ok(topicService.updateTopic(id, t));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseContentTopic> getById(@PathVariable Long id) {
        return ResponseEntity.ok(topicService.getById(id));
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<CourseContentTopic>> getByCourse(@PathVariable Long courseId) {
        return ResponseEntity.ok(topicService.getByCourse(courseId));
    }
}