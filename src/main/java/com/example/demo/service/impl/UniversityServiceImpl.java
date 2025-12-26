package com.example.demo.service.impl;

import com.example.demo.entity.University;
import com.example.demo.repository.UniversityRepository;
import com.example.demo.service.UniversityService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UniversityServiceImpl implements UniversityService {

    // ⚠️ field name must be exactly this
    private UniversityRepository repository;

    // ✅ REQUIRED by test cases
    public UniversityServiceImpl() {
    }

    // ✅ Used by Spring
    public UniversityServiceImpl(UniversityRepository repository) {
        this.repository = repository;
    }

    @Override
    public University createUniversity(University university) {

        // ✅ INVALID NAME CHECK (test31)
        if (university.getName() == null ||
            university.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid university name");
        }

        // ✅ DUPLICATE NAME CHECK (test03)
        if (repository.findByNameIgnoreCase(university.getName()).isPresent()) {
            throw new IllegalArgumentException("University already exists");
        }

        university.setActive(true);
        return repository.save(university);
    }

    @Override
    public University updateUniversity(Long id, University university) {

        University existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("not found"));

        if (university.getName() != null &&
            university.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid university name");
        }

        if (university.getName() != null) {
            existing.setName(university.getName());
        }

        if (university.getCountry() != null) {
            existing.setCountry(university.getCountry());
        }

        if (university.getAccreditationLevel() != null) {
            existing.setAccreditationLevel(university.getAccreditationLevel());
        }

        return repository.save(existing);
    }

    @Override
    public University getUniversityById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("not found"));
    }

    @Override
    public List<University> getAllUniversities() {
        return repository.findAll();
    }

    @Override
    public void deactivateUniversity(Long id) {
        University uni = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("not found"));
        uni.setActive(false);
        repository.save(uni);
    }
}
