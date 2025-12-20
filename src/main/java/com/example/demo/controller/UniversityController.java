package com.example.demo.controller;

import com.example.demo.entity.University;
import com.example.demo.service.UniversityService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/universities")
@Tag(name = "University Controller")
public class UniversityController {

    private final UniversityService universityService;

    public UniversityController(UniversityService universityService) {
        this.universityService = universityService;
    }

    @PostMapping("/")
    public ResponseEntity<University> create(@RequestBody University u) {
        return ResponseEntity.ok(universityService.createUniversity(u));
    }

    @PutMapping("/{id}")
    public ResponseEntity<University> update(@PathVariable Long id, @RequestBody University u) {
        return ResponseEntity.ok(universityService.updateUniversity(id, u));
    }

    @GetMapping("/{id}")
    public ResponseEntity<University> getById(@PathVariable Long id) {
        return ResponseEntity.ok(universityService.getById(id));
    }

    @GetMapping("/")
    public ResponseEntity<List<University>> getAll() {
        return ResponseEntity.ok(universityService.getAllUniversities());
    }

    @PutMapping("/{id}/deactivate")
    public ResponseEntity<Void> deactivate(@PathVariable Long id) {
        universityService.deactivate(id);
        return ResponseEntity.noContent().build();
    }
}