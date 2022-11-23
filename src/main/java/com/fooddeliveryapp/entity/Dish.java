package com.fooddeliveryapp.entity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "dishes")
public class Dish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dishId;
    
    @NotNull(message = "Dish name cannot be null")
    private String dishName;

    @NotNull(message = "Dish type cannot be null")
    private String dishType;

    @NotNull(message = "Dish image cannot be null")
    private String dishImage;

    @NotNull(message = "Dish description cannot be null")
    private String dishDescription;

    @NotNull(message = "Dish price cannot be null")
    @Min(value = 1,message = "Price should be greater than 0")
    private Double dishPrice;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name="restaurantId")
    private RestaurantOwner restaurantOwner;
}


