package com.example.demo.controller;

import com.example.demo.entity.University;
import com.example.demo.repository.UniversityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/universities")
public class UniversityController {

    @Autowired
    private UniversityRepository universityRepository;

    @GetMapping
    public List<University> getAllUniversities() {
        return universityRepository.findAll();
    }

    @PostMapping
    public University addUniversity(@RequestBody University university) {
        return universityRepository.save(university);
    }
}