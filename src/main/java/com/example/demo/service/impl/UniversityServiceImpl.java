package com.example.demo.service.impl;

import com.example.demo.entity.University;
import com.example.demo.repository.UniversityRepository;
import com.example.demo.service.UniversityService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service   // ⭐ MUST
public class UniversityServiceImpl implements UniversityService {

    // ⚠️ Test cases expect exact field name
    private UniversityRepository repository;

    public UniversityServiceImpl(UniversityRepository repository) {
        this.repository = repository;
    }

    @Override
    public University createUniversity(University university) {

        // ✅ NULL / BLANK validation
        if (university == null ||
                university.getName() == null ||
                university.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid university name");
        }

        String name = university.getName().trim();

        // ✅ DUPLICATE validation (TEST 03)
        if (repository.findByNameIgnoreCase(name).isPresent()) {
            throw new IllegalArgumentException("Duplicate university name");
        }

        university.setName(name);
        university.setActive(true);

        return repository.save(university);
    }

    @Override
    public University updateUniversity(Long id, University university) {

        University existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("not found"));

        if (university.getName() != null &&
                !university.getName().trim().isEmpty()) {
            existing.setName(university.getName().trim());
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
