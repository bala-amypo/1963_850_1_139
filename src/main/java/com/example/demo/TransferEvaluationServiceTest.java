package com.example.demo;

import com.example.demo.dto.TransferEvaluationResponse;
import com.example.demo.service.TransferEvaluationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TransferEvaluationServiceTest {

    @Autowired
    private TransferEvaluationService evaluationService;

    @Test
    void contextLoads() {
        // Ithu simple-ah spring context load aaguthanu check pannum
        assertNotNull(evaluationService);
    }
}