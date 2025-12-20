package com.example.demo.service;

import com.example.demo.entity.University;
import java.util.List;

public interface UniversityService {
    // Rule 6.2: Create a new university
    University createUniversity(University university);
    
    // Rule 6.2: Find university by ID (handles "not found")
    University getById(Long id);
    
    List<University> getAllUniversities();
}