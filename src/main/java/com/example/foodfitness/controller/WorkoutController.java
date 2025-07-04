package com.example.foodfitness.controller;

import com.example.foodfitness.entity.Workout;
import com.example.foodfitness.entity.User;
import com.example.foodfitness.entity.WorkoutType;
import com.example.foodfitness.service.WorkoutService;
import com.example.foodfitness.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/workouts")
public class WorkoutController {
    @Autowired
    private WorkoutService workoutService;
    @Autowired
    private UserRepository userRepo;

    @GetMapping
    public String listWorkouts(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        User user = userRepo.findByUsername(userDetails.getUsername());
        List<Workout> workouts = workoutService.getWorkoutsByUser(user);
        int totalBurned = workouts.stream().mapToInt(Workout::getCaloriesBurned).sum();
        model.addAttribute("workouts", workouts);
        model.addAttribute("totalBurned", totalBurned);
        model.addAttribute("workout", new Workout());
        model.addAttribute("workoutTypes", WorkoutType.values());
        return "workouts";
    }

    @PostMapping
    public String addWorkout(@ModelAttribute Workout workout, @AuthenticationPrincipal UserDetails userDetails) {
        User user = userRepo.findByUsername(userDetails.getUsername());
        workoutService.saveWorkout(workout, user);
        return "redirect:/workouts";
    }
}
