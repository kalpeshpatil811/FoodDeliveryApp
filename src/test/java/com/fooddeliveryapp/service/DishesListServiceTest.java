package com.fooddeliveryapp.service;

import com.fooddeliveryapp.entity.Dish;
import com.fooddeliveryapp.exception.DishIdNotFoundException;
import com.fooddeliveryapp.exception.InvalidDishDataException;
import com.fooddeliveryapp.exception.InvalidDishDetailsException;
import com.fooddeliveryapp.repository.DishRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class DishesListServiceTest {

	@InjectMocks
	private DishServiceImpl service;

	@Mock
	private DishRepository repository;

	@InjectMocks
	private Dish dish;

	@BeforeEach
	public void setUp() {
		dish.setDishId(50L);
		dish.setDishName("Thali");
		dish.setDishType("Breakfast");
		dish.setDishDescription("Delicious");
		dish.setDishImage("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQx8kSytk9hXLzePdsBDL10J1118x-IZ_WQiA&usqp=CAU");
		dish.setDishPrice(50d);
	}

	@Test
	public void testAddDish() throws InvalidDishDetailsException {
		Mockito.doReturn(dish).when(repository).save(Mockito.any());
		assertEquals(dish.getDishId(),service.addDish(dish).getDishId());
		assertEquals(dish.getDishName(), service.addDish(dish).getDishName());
		assertEquals(dish.getDishType(),service.addDish(dish).getDishType());
		assertEquals(dish.getDishDescription(),service.addDish(dish).getDishDescription());
		assertEquals(dish.getDishPrice(),service.addDish(dish).getDishPrice());
	}

	@Test
	public void testGetAllDishes(){
		Mockito.doReturn(Stream.of(dish, dish).collect(Collectors.toList())).when(repository).findAll();
		assertEquals(Stream.of(dish, dish).collect(Collectors.toList()),service.getAllDishes());
		assertEquals(2,service.getAllDishes().size());
	}

	@Test
	public void updateDish() throws InvalidDishDetailsException{
		Mockito.doReturn(dish).when(repository).save(Mockito.any());
		assertEquals(dish.getDishId(),service.updateDish(dish).getDishId());
		assertEquals(dish.getDishName(), service.updateDish(dish).getDishName());
		assertEquals(dish.getDishType(),service.updateDish(dish).getDishType());
		assertEquals(dish.getDishDescription(),service.updateDish(dish).getDishDescription());
		assertEquals(dish.getDishPrice(),service.updateDish(dish).getDishPrice());
	}

	@Test
	public void testGetDishById() throws DishIdNotFoundException {
		Long dishId=2L;
		Mockito.when(repository.findById(dishId)).thenReturn(Optional.of(dish));
		assertEquals(dish, service.getDishById(dishId));
		assertEquals(dish.getDishId(),service.getDishById(dishId).getDishId());
	}

	public void testDeleteDishById() throws InvalidDishDataException {
		Long dishId = 1l;
		service.deleteDishById(dishId);
		Mockito.verify(repository, Mockito.atLeastOnce()).deleteById(Mockito.anyLong());
	}
}
