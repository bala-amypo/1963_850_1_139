package com.example.demo.service.impl;

import com.example.demo.entity.University;
import com.example.demo.repository.UniversityRepository;
import com.example.demo.service.UniversityService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UniversityServiceImpl implements UniversityService {

    // ⚠️ Test cases expect exact field name
    private UniversityRepository repository;

    // ✅ REQUIRED: no-args constructor (tests instantiate with new UniversityServiceImpl())
    public UniversityServiceImpl() {
    }

    // Optional constructor (Spring usage)
    public UniversityServiceImpl(UniversityRepository repository) {
        this.repository = repository;
    }

    @Override
    public University createUniversity(University university) {

        // ✅ FIX #1: Invalid name check (test31)
        if (university == null ||
            university.getName() == null ||
            university.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Name required");
        }

        // ✅ FIX #2: Duplicate name check (test03)
        if (repository.findByName(university.getName()).isPresent()) {
            throw new IllegalArgumentException("exists");
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
