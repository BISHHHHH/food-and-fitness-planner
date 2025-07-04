package com.example.foodfitness.entity;

public enum WorkoutType {
    WALKING(3.3),
    RUNNING(9.8),
    CYCLING(8.0),
    SWIMMING(6.0),
    YOGA(2.5),
    WEIGHTLIFTING(3.5),
    AEROBICS(6.5);

    private final double met;

    WorkoutType(double met) {
        this.met = met;
    }

    public double getMet() {
        return met;
    }
}
