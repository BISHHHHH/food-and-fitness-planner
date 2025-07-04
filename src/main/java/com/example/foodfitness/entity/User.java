package com.example.foodfitness.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;
    private String password;
    private String role; // "USER"

    // Profile fields
    private Integer age;
    private Double weight;
    private String gender; // "Male", "Female", "Other"
    private String activityLevel; // "Low", "Moderate", "High"
}
