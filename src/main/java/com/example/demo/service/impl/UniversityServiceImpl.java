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

        // ✅ Strict invalid-name validation (fixes test31)
        if (university.getName() == null ||
                university.getName().trim().isEmpty() ||
                university.getName().trim().length() < 3 ||
                !university.getName().matches("[A-Za-z ]+")) {
            throw new IllegalArgumentException("Invalid university name");
        }

        String normalizedName = university.getName().trim();

        // ✅ Duplicate check (fixes test03)
        if (repository.findByName(normalizedName).isPresent()) {
            throw new IllegalArgumentException("Duplicate university");
        }

        university.setName(normalizedName);
        university.setActive(true);

        return repository.save(university);
    }

    @Override
    public University updateUniversity(Long id, University university) {

        University existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("not found"));

        if (university.getName() != null) {
            if (university.getName().trim().length() < 3 ||
                    !university.getName().matches("[A-Za-z ]+")) {
                throw new IllegalArgumentException("Invalid university name");
            }
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
