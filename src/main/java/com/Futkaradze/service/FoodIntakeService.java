package com.Futkaradze.service;

import com.Futkaradze.entity.FoodIntake;
import com.Futkaradze.exception.FoodIntakeException;
import com.Futkaradze.exception.UserException;

import java.time.LocalDate;
import java.util.List;

public interface FoodIntakeService {

    FoodIntake addFoodIntake(Long id, List<Long> dishIds, LocalDate date) throws UserException;

    List<FoodIntake> getFoodIntakesByUserId(Long userId) throws FoodIntakeException, UserException;

    void deleteFoodIntake(Long id) throws FoodIntakeException;
}

