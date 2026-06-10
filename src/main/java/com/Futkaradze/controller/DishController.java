package com.Futkaradze.controller;

import com.Futkaradze.entity.Dish;
import com.Futkaradze.exception.DishException;
import com.Futkaradze.service.DishService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/dishes")
public class DishController {

    private final DishService dishService;

    @PostMapping
    public ResponseEntity<Dish> createMeal(@RequestBody @Valid Dish dish) {
        Dish createdMeal = dishService.createDish(dish);
        return new ResponseEntity<>(createdMeal, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Dish>> getAllDishes() {
        List<Dish> dishes = dishService.getAllDishes();
        return new ResponseEntity<>(dishes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dish> getDishById(@PathVariable Long id) throws DishException {
        Dish dish = dishService.getDishById(id);
        return new ResponseEntity<>(dish, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Dish> updateDish(@RequestBody @Valid Dish dish,
                                           @PathVariable Long id) throws DishException {
        Dish updatedDish = dishService.updateDish(id, dish);
        return new ResponseEntity<>(updatedDish, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDishById(@PathVariable Long id) throws Exception {
        
        dishService.deleteDish(id);
        return new ResponseEntity<>("Dish deleted", HttpStatus.ACCEPTED);
    }
}