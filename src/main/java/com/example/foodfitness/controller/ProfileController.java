package com.example.foodfitness.controller;

import com.example.foodfitness.entity.User;
import com.example.foodfitness.repository.UserRepository;
import com.example.foodfitness.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/profile")
public class ProfileController {
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private UserService userService;

    @GetMapping
    public String profile(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        User user = userRepo.findByUsername(userDetails.getUsername());
        model.addAttribute("user", user);
        return "profile";
    }

    @PostMapping
    public String updateProfile(@AuthenticationPrincipal UserDetails userDetails,
                                @RequestParam Integer age,
                                @RequestParam Double weight,
                                @RequestParam String gender,
                                @RequestParam String activityLevel) {
        User user = userRepo.findByUsername(userDetails.getUsername());
        userService.updateProfile(user, age, weight, gender, activityLevel);
        return "redirect:/dashboard";
    }
}
