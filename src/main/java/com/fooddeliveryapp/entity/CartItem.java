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
@Table(name = "cartItems")
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartItemId;

    @NotNull(message = "Cart Item name cannot be null")
    private String cartItemName;

    @NotNull(message = "Cart Item type cannot be null")
    private String cartItemType;

    @NotNull(message = "Cart Item image cannot be null")
    private String cartItemImage;

    @NotNull(message = "Cart Item description cannot be null")
    private String cartItemDescription;

    @NotNull(message = "Cart Item price cannot be null")
    @Min(value = 0,message = "Price should be greater than 0")
    private Double cartItemPrice;

    @NotNull(message = "Cart Item Quantity cannot be null")
    @Min(value = 0,message = "Quantity should be greater than 0")
    private Integer cartItemQuantity;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "cartId")
    private Cart cart;

}