package com.fooddeliveryapp.repository;

import com.fooddeliveryapp.entity.Cart;
import com.fooddeliveryapp.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    void deleteAllByCart(Cart cart);
}
