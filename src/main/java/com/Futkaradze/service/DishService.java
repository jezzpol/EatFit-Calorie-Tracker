package com.Futkaradze.service;

import com.Futkaradze.entity.Dish;
import com.Futkaradze.exception.DishException;

import java.util.List;

public interface DishService {

    Dish createDish(Dish dish);

    Dish getDishById(Long id) throws DishException;

    List<Dish> getAllDishes();

    void deleteDish(Long id) throws DishException;

    Dish updateDish(Long id, Dish updatedDish) throws DishException;
}
