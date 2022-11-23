package com.fooddeliveryapp.service;

import com.fooddeliveryapp.exception.CartItemDoesNotExistsException;
import com.fooddeliveryapp.exception.CartNotFoundException;
import com.fooddeliveryapp.exception.InvalidCartItemDataException;
import com.fooddeliveryapp.entity.Cart;

public interface CartService {

    public Cart getCartById(Long cartId) throws CartNotFoundException;

    public Cart addToCart(Long cartId, Long dishId) throws CartNotFoundException, InvalidCartItemDataException;

    public Cart increaseQuantity(Long cartId, Long cartItemId) throws CartNotFoundException, CartItemDoesNotExistsException, InvalidCartItemDataException;

    public Cart removeFromCart(Long cartId, Long cartItemId) throws CartNotFoundException, CartItemDoesNotExistsException;

    public Cart removeAllFromCart(Long cartId) throws CartNotFoundException;

}
