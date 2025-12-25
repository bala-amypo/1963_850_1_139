package com.example.demo.service.impl;

import com.example.demo.entity.University;
import com.example.demo.repository.UniversityRepository;
import com.example.demo.service.UniversityService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UniversityServiceImpl implements UniversityService {

    // ⚠️ Test expects this exact field name
    private UniversityRepository repository;

    // ⭐ REQUIRED NO-ARG CONSTRUCTOR (FOR TESTS)
    public UniversityServiceImpl() {
    }

    // ⭐ Constructor injection for Spring
    public UniversityServiceImpl(UniversityRepository repository) {
        this.repository = repository;
    }

    @Override
    public University createUniversity(University university) {

        if (university.getName() == null ||
                university.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("name");
        }

        university.setActive(true);
        return repository.save(university);
    }

    @Override
    public University updateUniversity(Long id, University university) {

        University existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("not found"));

        if (university.getName() != null) {
            existing.setName(university.getName());
        }

        if (university.getCountry() != null) {
            existing.setCountry(university.getCountry());
        }

        if (university.getAccreditationLevel() != null) {
            existing.setAccreditationLevel(
                    university.getAccreditationLevel());
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
