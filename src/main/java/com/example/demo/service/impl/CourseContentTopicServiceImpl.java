package com.example.demo.service.impl;

import com.example.demo.entity.CourseContentTopic;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.CourseContentTopicRepository;
import com.example.demo.service.CourseContentTopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseContentTopicServiceImpl implements CourseContentTopicService {
    
    private final CourseContentTopicRepository topicRepository;

    @Override
    public CourseContentTopic createTopic(CourseContentTopic topic) {
        return topicRepository.save(topic);
    }

    @Override
    public CourseContentTopic updateTopic(Long id, CourseContentTopic topicDetails) {
        CourseContentTopic topic = getTopicById(id);
        
        // Ippo getName() and getDescription() error varaathu
        topic.setName(topicDetails.getName());
        topic.setDescription(topicDetails.getDescription());
        topic.setWeightPercentage(topicDetails.getWeightPercentage());
        
        return topicRepository.save(topic);
    }

    @Override
    public List<CourseContentTopic> getTopicsByCourseId(Long courseId) {
        return topicRepository.findByCourseId(courseId);
    }

    @Override
    public CourseContentTopic getTopicById(Long id) {
        return topicRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Topic not found with id: " + id));
    }
}