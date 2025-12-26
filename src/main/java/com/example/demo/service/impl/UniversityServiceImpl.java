package com.example.demo.service.impl;

import com.example.demo.entity.University;
import com.example.demo.repository.UniversityRepository;
import com.example.demo.service.UniversityService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UniversityServiceImpl implements UniversityService {

    // ⚠️ Test expects EXACT field name
    private UniversityRepository repository;

    // ✅ REQUIRED by TestNG
    public UniversityServiceImpl() {
    }

    // ✅ Used by Spring
    public UniversityServiceImpl(UniversityRepository repository) {
        this.repository = repository;
    }

    @Override
    public University createUniversity(University university) {

        // ---------- INVALID NAME CHECK ----------
        if (university == null ||
            university.getName() == null ||
            university.getName().trim().isEmpty() ||
            !university.getName().matches("[A-Za-z ]+")) {

            throw new IllegalArgumentException("Invalid university name");
        }

        String name = university.getName().trim();

        // ---------- DUPLICATE CHECK (CASE-INSENSITIVE) ----------
        if (repository != null) {
            for (University u : repository.findAll()) {
                if (u.getName() != null &&
                    u.getName().equalsIgnoreCase(name)) {

                    throw new IllegalArgumentException("University already exists");
                }
            }
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
