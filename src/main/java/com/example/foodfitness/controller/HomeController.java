package com.example.foodfitness.controller;

import com.example.foodfitness.entity.Food;
import com.example.foodfitness.entity.User;
import com.example.foodfitness.entity.Workout;
import com.example.foodfitness.repository.UserRepository;
import com.example.foodfitness.service.UserService;
import com.example.foodfitness.service.FoodService;
import com.example.foodfitness.service.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private UserService userService;
    @Autowired
    private FoodService foodService;

    @Autowired
    private WorkoutService workoutService;

    @GetMapping({"/", "/home"})
    public String home() { return "home"; }

    @GetMapping("/dashboard")
    public String dashboard(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        User user = userRepo.findByUsername(userDetails.getUsername());
        int suggestedCalories = userService.suggestCalories(user);
        String exerciseSuggestion = userService.suggestExercise(user);

        List<Food> foods = foodService.getFoodsByUser(user);
        int totalCalories = foods.stream().mapToInt(Food::getCalories).sum();

        List<Workout> workouts = workoutService.getWorkoutsByUser(user);
        int totalBurned = workouts.stream().mapToInt(Workout::getCaloriesBurned).sum();

        model.addAttribute("user", user);
        model.addAttribute("suggestedCalories", suggestedCalories);
        model.addAttribute("exerciseSuggestion", exerciseSuggestion);
        model.addAttribute("totalCalories", totalCalories);
        model.addAttribute("totalBurned", totalBurned);
        model.addAttribute("foods", foods);
        model.addAttribute("workouts", workouts);
        return "dashboard";
    }

}
