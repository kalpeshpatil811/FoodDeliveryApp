package com.fooddeliveryapp.service;

import com.fooddeliveryapp.entity.Dish;
import com.fooddeliveryapp.exception.DishIdNotFoundException;
import com.fooddeliveryapp.exception.DishNameNotFoundException;
import com.fooddeliveryapp.exception.InvalidDishDataException;
import com.fooddeliveryapp.exception.InvalidDishDetailsException;
import com.fooddeliveryapp.repository.DishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DishServiceImpl implements DishService {

    @Autowired
    private DishRepository dishRepo;

    public Dish addDish(Dish dish) throws InvalidDishDetailsException {
        List<Dish> dishes=dishRepo.findAll();
        for(Dish d:dishes) {
            if (d.getDishName().equals(dish.getDishName())) {
                throw new InvalidDishDetailsException("Dish already present, please enter new dish name");
            }
        }
        return dishRepo.save(dish);
    }

    @Override
    public List<Dish> getAllDishes() {
        return dishRepo.findAll();
    }

    @Override
    public Dish getDishById(Long dishId) throws DishIdNotFoundException {
        try {
            Dish dish1 = dishRepo.findById(dishId).get();
            return dish1;
        } catch (Exception e) {
            throw new DishIdNotFoundException("Id is not present, enter correct Id");
        }
    }

    @Override
    public List<Dish> getDishByName(String dishName) throws DishNameNotFoundException {
        List<Dish> dishes=dishRepo.findAllByDishName(dishName);
        if(dishes.size()>=1){
            return dishes;
        }
        else{
            throw  new DishNameNotFoundException("This dish is not present, enter correct dish name.");
        }
    }

    @Override
    public Dish updateDish(Dish dishes) throws InvalidDishDetailsException {
        List<Dish> dishes1=dishRepo.findAll();
//        for(Dish d:dishes1) {
//            if (d.getDishName().equals(dishes.getDishName())) {
//                throw new InvalidDishDetailsException("Dish already present, please enter new dish name");
//            }
//        }
        return dishRepo.save(dishes);
    }

    @Override
    public List<Dish> deleteDishById(Long dishId) throws InvalidDishDataException {
        try {
            dishRepo.deleteById(dishId);
            return dishRepo.findAll();
        }catch (Exception e){
            throw new InvalidDishDataException("No such dish exist, enter correct id");
        }
    }
}
