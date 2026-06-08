package com.Futkaradze.repository;

import com.Futkaradze.entity.FoodIntake;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface FoodIntakeRepository extends JpaRepository<FoodIntake, Long> {

    List<FoodIntake> findByUserId(Long userId);

    List<FoodIntake> findByUserIdAndDate(Long userId, LocalDate date);
}
