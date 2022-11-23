package com.fooddeliveryapp.service;

import com.fooddeliveryapp.entity.Dish;
import com.fooddeliveryapp.exception.DishIdNotFoundException;
import com.fooddeliveryapp.exception.DishNameNotFoundException;
import com.fooddeliveryapp.exception.InvalidDishDataException;
import com.fooddeliveryapp.exception.InvalidDishDetailsException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DishService {

    public Dish addDish(Dish dishes) throws InvalidDishDetailsException;

    public List<Dish> getAllDishes();

    public Dish getDishById(Long dishId) throws DishIdNotFoundException;

    public List<Dish> getDishByName(String dishName) throws DishNameNotFoundException;

    public Dish updateDish(Dish dishes) throws InvalidDishDetailsException;

    public List<Dish> deleteDishById(Long dishId) throws InvalidDishDataException;
}
