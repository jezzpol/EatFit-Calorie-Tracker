package com.Futkaradze.service.impl;

import com.Futkaradze.entity.Dish;
import com.Futkaradze.entity.FoodIntake;
import com.Futkaradze.entity.User;
import com.Futkaradze.exception.FoodIntakeException;
import com.Futkaradze.exception.UserException;
import com.Futkaradze.repository.DishRepository;
import com.Futkaradze.repository.FoodIntakeRepository;
import com.Futkaradze.repository.UserRepository;
import com.Futkaradze.service.FoodIntakeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FoodIntakeServiceImpl implements FoodIntakeService {

    private final UserRepository userRepository;
    private final DishRepository dishRepository;
    private final FoodIntakeRepository foodIntakeRepository;


    @Override
    public FoodIntake addFoodIntake(Long id, List<Long> dishIds, LocalDate date) throws UserException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserException("User not found with id: " + id));

        List<Dish> dishes = dishRepository.findAllById(dishIds);

        FoodIntake foodIntake = new FoodIntake();
        foodIntake.setUser(user);
        foodIntake.setDate(date);
        foodIntake.setDishes(dishes);

        return foodIntakeRepository.save(foodIntake);
    }

    @Override
    public List<FoodIntake> getFoodIntakesByUserId(Long userId) throws UserException {
        if (!userRepository.existsById(userId)) {
            throw new UserException("User not found with id: " + userId);
        }
        return foodIntakeRepository.findByUserId(userId);
    }

    @Override
    public void deleteFoodIntake(Long id) throws FoodIntakeException {
        if (!foodIntakeRepository.existsById(id)) {
            throw new FoodIntakeException("FoodIntake not found with id: " + id);
        }
        foodIntakeRepository.deleteById(id);
    }
}
