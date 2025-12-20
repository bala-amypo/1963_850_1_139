package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "universities")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class University {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    private String accreditationLevel;
    private String country;

    @Column(nullable = false)
    private Boolean active = true; // Defaults to true
}