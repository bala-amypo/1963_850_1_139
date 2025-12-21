package com.example.demo.controller;

import com.example.demo.entity.University;
import com.example.demo.service.UniversityService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/universities")
@RequiredArgsConstructor
@Tag(name = "University")
public class UniversityController {
    private final UniversityService universityService;

    @PostMapping("/")
    public ResponseEntity<University> create(@RequestBody University univ) {
        return ResponseEntity.ok(universityService.createUniversity(univ));
    }

    @PutMapping("/{id}")
    public ResponseEntity<University> update(@PathVariable Long id, @RequestBody University univ) {
        return ResponseEntity.ok(universityService.updateUniversity(id, univ));
    }

    @GetMapping("/{id}")
    public ResponseEntity<University> getById(@PathVariable Long id) {
        return ResponseEntity.ok(universityService.getUniversityById(id));
    }

    @GetMapping("/")
    public ResponseEntity<List<University>> getAll() {
        return ResponseEntity.ok(universityService.getAllUniversities());
    }

    @PutMapping("/{id}/deactivate")
    public void deactivate(@PathVariable Long id) {
        universityService.deactivateUniversity(id);
    }
}