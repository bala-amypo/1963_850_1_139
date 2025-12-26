package com.example.demo.service;

import com.example.demo.entity.University;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface UniversityService {

    University createUniversity(University univ);

    University updateUniversity(Long id, University univ);

    University getUniversityById(Long id);

    List<University> getAllUniversities();

    void deactivateUniversity(Long id);
}
