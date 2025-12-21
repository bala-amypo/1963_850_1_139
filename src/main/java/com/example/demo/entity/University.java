package com.example.demo.entity;
import jakarta.persistence.*;
import lombok.Data;

@Entity @Table(name = "universities") @Data
public class University {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;
    private String country;
}