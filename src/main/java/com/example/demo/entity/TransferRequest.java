package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Entity
@Data
@Table(name = "transfer_requests")
public class TransferRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "source_university_id")
    private University sourceUniversity;

    @ManyToOne
    @JoinColumn(name = "target_university_id")
    private University targetUniversity;

    private String status; // PENDING, APPROVED, REJECTED
    private Date requestDate;
}