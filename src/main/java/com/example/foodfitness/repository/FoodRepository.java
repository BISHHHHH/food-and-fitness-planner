package com.example.foodfitness.repository;
import com.example.foodfitness.entity.Food;
import com.example.foodfitness.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface FoodRepository extends JpaRepository<Food, Long> {
    List<Food> findByUser(User user);
}