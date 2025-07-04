package com.example.foodfitness.service;
import com.example.foodfitness.entity.Food;
import com.example.foodfitness.entity.User;
import com.example.foodfitness.repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FoodService {
    @Autowired
    private FoodRepository foodRepo;
    public List<Food> getFoodsByUser(User user) {
        return foodRepo.findByUser(user);
    }
    public Food saveFood(Food food) {
        return foodRepo.save(food);
    }
}
