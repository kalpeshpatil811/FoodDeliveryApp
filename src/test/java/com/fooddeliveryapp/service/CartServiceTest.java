package com.fooddeliveryapp.service;

import com.fooddeliveryapp.entity.Cart;
import com.fooddeliveryapp.entity.CartItem;
import com.fooddeliveryapp.exception.CartItemDoesNotExistsException;
import com.fooddeliveryapp.exception.CartNotFoundException;
import com.fooddeliveryapp.exception.InvalidCartItemDataException;
import com.fooddeliveryapp.repository.CartItemRepository;
import com.fooddeliveryapp.repository.CartRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class CartServiceTest {

    @InjectMocks
    private CartServiceImpl cartService;

    @Mock
    private CartRepository cartRepository;

    @Mock
    private CartItemRepository cartItemRepository;

    @InjectMocks
    private Cart cart;

    @InjectMocks
    CartItem cartItem;

    @BeforeEach
    public void setUp() {
        cart.setCartId(1L);
        cart.setTotalAmount(0.0);

        cartItem.setCartItemId(1L);
        cartItem.setCartItemName("Thali");
        cartItem.setCartItemType("Lunch");
        cartItem.setCartItemImage("C:\\Users\\kdigamba\\OneDrive - Capgemini\\Pictures\\wallpaper");
        cartItem.setCartItemDescription("Delicious Thali");
        cartItem.setCartItemPrice(120.0);
        cartItem.setCartItemQuantity(1);
        cartItem.setCart(cart);

        cart.setCartItems(Stream.of(cartItem).collect(Collectors.toList()));
    }

    @Test
    public void testGetCartById() throws CartNotFoundException {
        Long cartId = 1L;
        when(cartRepository.findById(cartId)).thenReturn(Optional.of(cart));
        assertEquals(cart, cartService.getCartById(cartId));
        assertEquals(cart.getCartId(), cartService.getCartById(cartId).getCartId());
    }

    @Test
    public void testCartItems() throws CartNotFoundException {
        Long cartId = 1L;
        when(cartRepository.findById(cartId)).thenReturn(Optional.of(cart));
        assertEquals(cart.getCartItems(),cartService.getCartById(cartId).getCartItems());
        assertEquals(cart.getTotalAmount(),cartService.getCartById(cartId).getTotalAmount());
    }

//    @Test
//    public void testIncreaseQuantity() throws InvalidCartItemDataException, CartNotFoundException, CartItemDoesNotExistsException {
//        Long cartId=1L;
//        Long cartItemId=1L;
//        when(cartRepository.findById(cartId)).thenReturn(Optional.of(cart));
//        when(cartService.getCartById(cartId).getCartItems()).thenReturn(cart.getCartItems());
//
//        assertEquals(cart.getTotalAmount(),cartService.getCartById(cartId).getTotalAmount());
//        assertEquals(cart.getCartItems(),cartService.increaseQuantity(cartId,cartItemId).getCartItems());
//    }


//    @Test
//    public void testRemoveAllFromCart() throws CartNotFoundException {
//        Long cartId = 1L;
//        when(cartRepository.findById(cartId)).thenReturn(Optional.of(cart));
//        when(cartService.removeAllFromCart(cartId)).thenReturn(cart);
//        cartItemRepository.deleteAllByCart(cart);
//
//        assertEquals(0, cartService.removeAllFromCart(cartId).getCartItems().size());
//    }

}
