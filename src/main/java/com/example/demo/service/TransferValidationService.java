package com.example.demo.service;

public interface TransferValidationService {
    boolean validateTransfer(Long sourceCourseId, Long targetCourseId);
}