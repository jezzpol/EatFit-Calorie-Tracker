package com.Futkaradze.responses;

import com.Futkaradze.entity.Dish;
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
public class FoodIntakeResponse {
    private Long id;

    private LocalDate date;

    private Long userId;

    private List<Dish> dishes;
}
