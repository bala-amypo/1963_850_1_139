package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "course_content_topics")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseContentTopic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Field names-ah service-ku yetha maari maathiyachu
    private String name; 
    private String description;
    private Double weightPercentage;

    @Column(name = "course_id")
    private Long courseId; // Relationship-ah vida ID handling compile-ku easy-ah irukum
}