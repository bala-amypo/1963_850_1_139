package com.example.demo.controller;

import com.example.demo.entity.TransferApplication;
import com.example.demo.entity.User;
import com.example.demo.repository.TransferApplicationRepository;
import com.example.demo.repository.UniversityRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transfers")
public class TransferController {

    @Autowired
    private TransferApplicationRepository transferRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private UniversityRepository uniRepo;

    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/apply/{universityId}")
    public ResponseEntity<?> applyTransfer(
            @RequestHeader("Authorization") String token,
            @PathVariable Long universityId,
            @RequestBody TransferApplication application) {

        // 1. Token-la irunthu user-ah kandupudikkirom
        String email = jwtUtils.getEmailFromToken(token.substring(7));
        User student = userRepo.findByEmail(email).orElseThrow();

        // 2. University-ah kandupudikkirom
        var university = uniRepo.findById(universityId).orElseThrow();

        // 3. Application details set panrom
        application.setStudent(student);
        application.setTargetUniversity(university);
        application.setStatus("PENDING");

        transferRepo.save(application);
        return ResponseEntity.ok("Transfer application submitted successfully!");
    }

    @GetMapping("/my-applications")
    public List<TransferApplication> getMyApplications(@RequestHeader("Authorization") String token) {
        String email = jwtUtils.getEmailFromToken(token.substring(7));
        User student = userRepo.findByEmail(email).orElseThrow();
        return transferRepo.findByStudentId(student.getId());
    }
}