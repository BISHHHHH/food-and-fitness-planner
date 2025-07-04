package com.example.foodfitness.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Workout {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private WorkoutType workoutType;

    private int duration; // in minutes

    private int caloriesBurned;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
