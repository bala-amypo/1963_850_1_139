package com.example.demo.service.impl;

import com.example.demo.entity.University;
import com.example.demo.repository.UniversityRepository;
import com.example.demo.service.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UniversityServiceImpl implements UniversityService {

    @Autowired
    private UniversityRepository universityRepository;

    @Override
    public University createUniversity(University university) {
        return universityRepository.save(university);
    }

    @Override
    public University updateUniversity(Long id, University universityDetails) {
        University university = getById(id);
        university.setName(universityDetails.getName());
        university.setLocation(universityDetails.getLocation());
        university.setActive(universityDetails.getActive());
        return universityRepository.save(university);
    }

    @Override
    public University getById(Long id) {
        return universityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("University not found with id: " + id));
    }

    @Override
    public List<University> getAll() {
        return universityRepository.findAll();
    }

    @Override
    public void deactivate(Long id) {
        University university = getById(id);
        university.setActive(false);
        universityRepository.save(university);
    }
}