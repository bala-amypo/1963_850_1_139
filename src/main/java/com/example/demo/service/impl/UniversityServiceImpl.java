package com.example.demo.service.impl;

import com.example.demo.entity.University;
import com.example.demo.repository.UniversityRepository;
import com.example.demo.service.UniversityService;

import java.util.List;

public class UniversityServiceImpl implements UniversityService {

    // ⚠️ Field name must be EXACT
    private UniversityRepository repository;

    @Override
    public University createUniversity(University univ) {
        if (univ == null || univ.getName() == null || univ.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Name required");
        }

        if (repository.findByName(univ.getName()).isPresent()) {
            throw new IllegalArgumentException("University already exists");
        }

        univ.setActive(true);
        return repository.save(univ);
    }

    @Override
    public University updateUniversity(Long id, University univ) {
        University existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("University not found"));

        if (univ.getName() != null) {
            existing.setName(univ.getName());
        }
        existing.setAccreditationLevel(univ.getAccreditationLevel());
        existing.setCountry(univ.getCountry());

        return repository.save(existing);
    }

    @Override
    public University getUniversityById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("University not found"));
    }

    @Override
    public List<University> getAllUniversities() {
        return repository.findAll();
    }

    @Override
    public void deactivateUniversity(Long id) {
        University u = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("University not found"));
        u.setActive(false);
        repository.save(u);
    }
}
