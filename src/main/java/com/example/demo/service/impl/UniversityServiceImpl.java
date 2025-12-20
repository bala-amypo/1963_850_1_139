package com.example.demo.service.impl;

import com.example.demo.entity.University;
import com.example.demo.repository.UniversityRepository;
import com.example.demo.service.UniversityService;
import com.example.demo.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UniversityServiceImpl implements UniversityService {
    private final UniversityRepository universityRepository;

    // Rule 6: Constructor injection only
    public UniversityServiceImpl(UniversityRepository universityRepository) {
        this.universityRepository = universityRepository;
    }

    @Override
    public University createUniversity(University university) {
        return universityRepository.save(university);
    }

    @Override
    public University getById(Long id) {
        return universityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("University not found with id: " + id));
    }

    @Override
    public List<University> getAllUniversities() {
        return universityRepository.findAll();
    }
    @Override
public void deactivate(Long id) {
    University university = universityRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("University not found"));
    university.setActive(false);
    universityRepository.save(university);
}
}