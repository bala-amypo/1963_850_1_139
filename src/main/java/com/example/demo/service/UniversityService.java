package com.example.demo.service;

import com.example.demo.entity.University;
import java.util.List;

public interface UniversityService {
    // Rule 6.2: Create universities
    University createUniversity(University university);

    // Rule 6.2: Fetch by ID (must handle not-found)
    University getById(Long id);

    // Optional but helpful: List all universities
    List<University> getAllUniversities();
}