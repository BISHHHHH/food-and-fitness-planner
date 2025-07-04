package com.example.foodfitness.service;

import com.example.foodfitness.entity.Workout;
import com.example.foodfitness.entity.User;
import com.example.foodfitness.entity.WorkoutType;
import com.example.foodfitness.repository.WorkoutRepository;
import com.example.foodfitness.util.CalorieCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkoutService {
    @Autowired
    private WorkoutRepository workoutRepo;

    public List<Workout> getWorkoutsByUser(User user) {
        return workoutRepo.findByUser(user);
    }

    public Workout saveWorkout(Workout workout, User user) {
        workout.setUser(user);
        double weight = user.getWeight() != null ? user.getWeight() : 70.0; // default 70kg
        int calories = CalorieCalculator.calculateCaloriesBurned(
                workout.getWorkoutType(), weight, workout.getDuration()
        );
        workout.setCaloriesBurned(calories);
        return workoutRepo.save(workout);
    }
}
