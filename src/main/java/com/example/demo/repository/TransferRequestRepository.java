package com.example.demo.repository;

import com.example.demo.entity.TransferRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TransferRequestRepository extends JpaRepository<TransferRequest, Long> {
    List<TransferRequest> findByUserId(Long userId);
}