package com.example.demo.service;

import com.example.demo.entity.CourseContentTopic;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CourseContentTopicService {

    CourseContentTopic createTopic(CourseContentTopic topic);

    List<CourseContentTopic> getTopicsForCourse(Long courseId);

    CourseContentTopic getTopicById(Long id);

    CourseContentTopic updateTopic(Long id, CourseContentTopic topic);
}
