package com.example.demo.controller;

import com.example.demo.entity.CourseContentTopic;
import com.example.demo.service.CourseContentTopicService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/topics")
public class CourseContentTopicController {

    private final CourseContentTopicService service;

    public CourseContentTopicController(CourseContentTopicService service) {
        this.service = service;
    }

    @PostMapping
    public CourseContentTopic create(@RequestBody CourseContentTopic t) {
        return service.createTopic(t);
    }

    @PutMapping("/{id}")
    public CourseContentTopic update(@PathVariable Long id,
                                     @RequestBody CourseContentTopic t) {
        return service.updateTopic(id, t);
    }

    @GetMapping("/{id}")
    public CourseContentTopic get(@PathVariable Long id) {
        return service.getTopicById(id);
    }

    @GetMapping("/course/{courseId}")
    public List<CourseContentTopic> getByCourse(@PathVariable Long courseId) {
        return service.getTopicsForCourse(courseId);
    }
}
