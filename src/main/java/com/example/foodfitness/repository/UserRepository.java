// UserRepository.java
package com.example.foodfitness.repository;
import com.example.foodfitness.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}