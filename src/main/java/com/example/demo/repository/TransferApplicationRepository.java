package com.example.demo.repository;

import com.example.demo.entity.TransferApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TransferApplicationRepository extends JpaRepository<TransferApplication, Long> {
    
    // Oru specific student-oda applications-ah mattum edukka intha method help pannum
    List<TransferApplication> findByStudentId(Long userId);
}