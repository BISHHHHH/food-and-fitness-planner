package com.example.foodfitness.service;

import com.example.foodfitness.entity.User;
import com.example.foodfitness.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private BCryptPasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);
        if (user == null) throw new UsernameNotFoundException("User not found");
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRole())
                .build();
    }

    public User registerUser(String username, String password) {
        User user = User.builder()
                .username(username)
                .password(encoder.encode(password))
                .role("USER")
                .build();
        return userRepo.save(user);
    }

    public User updateProfile(User user, Integer age, Double weight, String gender, String activityLevel) {
        user.setAge(age);
        user.setWeight(weight);
        user.setGender(gender);
        user.setActivityLevel(activityLevel);
        return userRepo.save(user);
    }

    // Calorie suggestion logic (Mifflin-St Jeor Equation)
    public int suggestCalories(User user) {
        if (user.getAge() == null || user.getWeight() == null || user.getGender() == null || user.getActivityLevel() == null)
            return 2000; // default
        double bmr;
        if ("Male".equalsIgnoreCase(user.getGender())) {
            bmr = 10 * user.getWeight() + 6.25 * 170 - 5 * user.getAge() + 5;
        } else {
            bmr = 10 * user.getWeight() + 6.25 * 160 - 5 * user.getAge() - 161;
        }
        double activityFactor = switch (user.getActivityLevel()) {
            case "Low" -> 1.2;
            case "Moderate" -> 1.55;
            case "High" -> 1.725;
            default -> 1.2;
        };
        return (int) (bmr * activityFactor);
    }

    // Simple exercise suggestion
    public String suggestExercise(User user) {
        if (user.getActivityLevel() == null) return "30 min walk";
        return switch (user.getActivityLevel()) {
            case "Low" -> "30 min brisk walk or yoga";
            case "Moderate" -> "45 min cycling or jogging";
            case "High" -> "60 min running, HIIT, or swimming";
            default -> "30 min walk";
        };
    }
}
