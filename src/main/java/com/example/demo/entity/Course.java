package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String courseName; // Renamed from name
    private String courseCode; // Renamed from code
    private Integer creditHours; // Renamed from credits
    
    @Column(length = 1000)
    private String description;

    private Boolean active = true; // Added missing field

    @ManyToOne
    @JoinColumn(name = "university_id")
    private University university; // Added missing relation for transfer logic

    @ManyToOne
    @JoinColumn(name = "program_id")
    private Program program;
}