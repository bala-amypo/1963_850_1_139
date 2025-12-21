package com.example.demo.repository;

import com.example.demo.entity.University;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UniversityRepository extends JpaRepository<University, Long> {
    List<University> findByNameContainingIgnoreCase(String name);
    List<University> findByCountryIgnoreCase(String country);
}