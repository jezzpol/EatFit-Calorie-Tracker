package com.Futkaradze.service.impl;

import com.Futkaradze.entity.FoodIntake;
import com.Futkaradze.entity.User;
import com.Futkaradze.entity.Dish;
import com.Futkaradze.exception.UserException;
import com.Futkaradze.repository.FoodIntakeRepository;
import com.Futkaradze.repository.UserRepository;
import com.Futkaradze.responses.CaloriesCheckResponse;
import com.Futkaradze.responses.DailyReportResponse;
import com.Futkaradze.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final UserRepository userRepository;
    private final FoodIntakeRepository foodIntakeRepository;


    @Override
    public DailyReportResponse getDailyHistory(Long id, LocalDate date)
            throws UserException {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserException("User not found with id: " + id));

        List<FoodIntake> foodIntakes = foodIntakeRepository.findByUserIdAndDate(id, date);

        int totalCalories = foodIntakes.stream()
                .flatMap(fi -> fi.getDishes().stream())
                .mapToInt(Dish::getCalories)
                .sum();

        DailyReportResponse response = new DailyReportResponse();
        response.setDate(date);
        response.setTotalCalories(totalCalories);
        response.setFoodIntakes(foodIntakes);
        response.setCaloriesNorm(user.getDailyCalories());

        return response;
    }

    @Override
    public CaloriesCheckResponse checkDailyCalories(Long id, LocalDate date)
            throws UserException {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserException("User not found with id: " + id));

        List<FoodIntake> foodIntakes = foodIntakeRepository.findByUserIdAndDate(id, date);

        int totalCalories = foodIntakes.stream()
                .flatMap(fi -> fi.getDishes().stream())
                .mapToInt(Dish::getCalories)
                .sum();

        boolean ExceedingLimit = totalCalories > user.getDailyCalories();

        CaloriesCheckResponse response = new CaloriesCheckResponse();
        response.setExceedingLimit(ExceedingLimit);
        response.setCaloriesConsumed(totalCalories);
        response.setCalorieNorm(user.getDailyCalories());

        return response;
    }

    @Override
    public Map<LocalDate, Integer> getCaloriesHistory(Long userId, LocalDate date) {

        List<FoodIntake> foodIntakes = foodIntakeRepository.findByUserIdAndDate(userId, date);

        return foodIntakes.stream()
                .collect(Collectors.groupingBy(
                        FoodIntake::getDate,
                        Collectors.summingInt(foodIntake ->
                                foodIntake.getDishes().stream()
                                        .mapToInt(Dish::getCalories)
                                        .sum()
                        )
                ));
    }
}
