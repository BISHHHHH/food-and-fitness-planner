package com.example.foodfitness.controller;

import com.example.foodfitness.entity.Food;
import com.example.foodfitness.entity.User;
import com.example.foodfitness.service.FoodService;
import com.example.foodfitness.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/foods")
public class FoodController {
    @Autowired
    private FoodService foodService;
    @Autowired
    private UserRepository userRepo;

    @GetMapping
    public String listFoods(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        User user = userRepo.findByUsername(userDetails.getUsername());
        List<Food> foods = foodService.getFoodsByUser(user);
        int totalCalories = foods.stream().mapToInt(Food::getCalories).sum();
        model.addAttribute("foods", foods);
        model.addAttribute("totalCalories", totalCalories);
        model.addAttribute("food", new Food());
        return "foods";
    }

    @PostMapping
    public String addFood(@ModelAttribute Food food, @AuthenticationPrincipal UserDetails userDetails) {
        User user = userRepo.findByUsername(userDetails.getUsername());
        food.setUser(user);
        foodService.saveFood(food);
        return "redirect:/foods";
    }
}
