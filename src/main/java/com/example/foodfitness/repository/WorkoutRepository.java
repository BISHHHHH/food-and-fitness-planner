package com.example.foodfitness.repository;
import com.example.foodfitness.entity.Workout;
import com.example.foodfitness.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface WorkoutRepository extends JpaRepository<Workout, Long> {
    List<Workout> findByUser(User user);
}