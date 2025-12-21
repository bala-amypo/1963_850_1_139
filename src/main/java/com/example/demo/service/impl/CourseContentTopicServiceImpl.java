package com.example.demo.service.impl;

import com.example.demo.entity.CourseContentTopic;
import com.example.demo.repository.CourseContentTopicRepository;
import com.example.demo.service.CourseContentTopicService;
import com.example.demo.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CourseContentTopicServiceImpl implements CourseContentTopicService {

    private final CourseContentTopicRepository topicRepository;

    public CourseContentTopicServiceImpl(CourseContentTopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    @Override
    public CourseContentTopic createTopic(CourseContentTopic topic) {
        return topicRepository.save(topic);
    }

    @Override
    public CourseContentTopic updateTopic(Long id, CourseContentTopic topicDetails) {
        CourseContentTopic topic = getById(id);
        topic.setName(topicDetails.getName());
        return topicRepository.save(topic);
    }

    @Override
    public CourseContentTopic getById(Long id) {
        return topicRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Topic not found"));
    }

    @Override
    public List<CourseContentTopic> getByCourse(Long courseId) {
        return topicRepository.findByCourseId(courseId);
    }
}