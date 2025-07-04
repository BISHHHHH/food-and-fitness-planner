package com.example.foodfitness.util;

import com.example.foodfitness.entity.WorkoutType;

public class CalorieCalculator {
    public static int calculateCaloriesBurned(WorkoutType type, double weightKg, int durationMinutes) {
        double met = type.getMet();
        double calories = durationMinutes * (met * 3.5 * weightKg) / 200.0;
        return (int) Math.round(calories);
    }
}
