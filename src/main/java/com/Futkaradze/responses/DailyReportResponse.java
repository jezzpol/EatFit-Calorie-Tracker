package com.Futkaradze.responses;

import com.Futkaradze.entity.FoodIntake;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DailyReportResponse {
    private LocalDate date;

    private Integer totalCalories;

    private List<FoodIntake> foodIntakes;

    private Integer CaloriesNorm;
}

