package com.example.demo.service;

import com.example.demo.entity.CourseContentTopic;
import java.util.List;

public interface CourseContentTopicService {
    CourseContentTopic createTopic(CourseContentTopic topic);
    CourseContentTopic getTopicById(Long id);
    // Intha rendu methods thaan controller-ku venum
    CourseContentTopic updateTopic(Long id, CourseContentTopic topic);
    List<CourseContentTopic> getTopicsByCourseId(Long courseId); 
}