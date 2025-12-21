package com.example.demo.controller;

import com.example.demo.entity.CourseContentTopic;
import com.example.demo.service.CourseContentTopicService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/topics")
@RequiredArgsConstructor
@Tag(name = "Course Content Topic")
public class CourseContentTopicController {

    private final CourseContentTopicService topicService;

    @PostMapping("/")
    @Operation(summary = "Create a new topic")
    public ResponseEntity<CourseContentTopic> create(@RequestBody CourseContentTopic topic) {
        return ResponseEntity.ok(topicService.createTopic(topic));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing topic")
    public ResponseEntity<CourseContentTopic> update(@PathVariable Long id, @RequestBody CourseContentTopic topic) {
        return ResponseEntity.ok(topicService.updateTopic(id, topic));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get topic by id")
    public ResponseEntity<CourseContentTopic> getById(@PathVariable Long id) {
        return ResponseEntity.ok(topicService.getTopicById(id));
    }

    @GetMapping("/course/{courseId}")
    @Operation(summary = "Get all topics for a specific course")
    public ResponseEntity<List<CourseContentTopic>> getByCourse(@PathVariable Long courseId) {
        return ResponseEntity.ok(topicService.getTopicsByCourseId(courseId));
    }
}