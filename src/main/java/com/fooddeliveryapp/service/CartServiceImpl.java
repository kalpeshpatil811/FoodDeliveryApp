package com.fooddeliveryapp.service;

import com.fooddeliveryapp.exception.CartItemDoesNotExistsException;
import com.fooddeliveryapp.exception.CartNotFoundException;
import com.fooddeliveryapp.exception.InvalidCartItemDataException;
import com.fooddeliveryapp.entity.Cart;
import com.fooddeliveryapp.entity.CartItem;
import com.fooddeliveryapp.entity.Dish;
import com.fooddeliveryapp.repository.CartItemRepository;
import com.fooddeliveryapp.repository.CartRepository;
import com.fooddeliveryapp.repository.DishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private DishRepository dishRepository;

    @Override
    public Cart getCartById(Long cartId) throws CartNotFoundException {
        try {
            return cartRepository.findById(cartId).get();
        } catch (Exception e) {
            throw new CartNotFoundException("Cart id does not exists");
        }
    }

    @Override
    public Cart addToCart(Long cartId, Long dishId) throws CartNotFoundException, InvalidCartItemDataException {
        Cart cart;
        try {
            cart = cartRepository.findById(cartId).get();
        } catch (Exception e) {
            throw new CartNotFoundException("Cart id does not exists");
        }
        Dish dish = dishRepository.findById(dishId).get();
        List<CartItem> cartItems = cart.getCartItems();

        try {
            for (CartItem cI : cartItems) {
                if (cI.getCartItemName().equals(dish.getDishName())) {
                    cI.setCartItemQuantity(cI.getCartItemQuantity() + 1);
                    cart.setCartItems(cartItems);
                    cart.setTotalAmount(cart.getTotalAmount() + cI.getCartItemPrice());
                    return cartRepository.save(cart);
                }
            }

            CartItem cartItem = new CartItem();
            cartItem.setCartItemName(dish.getDishName());
            cartItem.setCartItemType(dish.getDishType());
            cartItem.setCartItemDescription(dish.getDishDescription());
            cartItem.setCartItemImage(dish.getDishImage());
            cartItem.setCartItemPrice(dish.getDishPrice());
            cartItem.setCartItemQuantity(1);
            cartItem.setCart(cart);

            cartItems.add(cartItem);
            cart.setCartItems(cartItems);
            cart.setTotalAmount(cart.getTotalAmount() + cartItem.getCartItemPrice());
            return cartRepository.save(cart);
        } catch (Exception e) {
            throw new InvalidCartItemDataException("Enter correct cart item data");
        }
    }

    @Override
    public Cart increaseQuantity(Long cartId, Long cartItemId) throws CartNotFoundException, CartItemDoesNotExistsException, InvalidCartItemDataException {
        Cart cart;
        try {
            cart = cartRepository.findById(cartId).get();
        } catch (Exception e) {
            throw new CartNotFoundException("Cart id does not exists");
        }
        List<CartItem> cartItems = cart.getCartItems();
        try {
            for (CartItem cI : cartItems) {
                if (cI.getCartItemId() == cartItemId) {
                    cart.setTotalAmount(cart.getTotalAmount() + cI.getCartItemPrice());
                    cI.setCartItemQuantity(cI.getCartItemQuantity() + 1);
                    break;
                }
            }
        } catch (Exception e) {
            throw new CartItemDoesNotExistsException("Cart item does not exists");
        }
        cart.setCartItems(cartItems);
        return cartRepository.save(cart);
    }

    @Override
    public Cart removeFromCart(Long cartId, Long cartItemId) throws CartNotFoundException, CartItemDoesNotExistsException {
        Cart cart;
        try {
            cart = cartRepository.findById(cartId).get();
        } catch (Exception e) {
            throw new CartNotFoundException("Cart id does not exists");
        }
        List<CartItem> cartItems = cart.getCartItems();
        try {
            for (CartItem cI : cartItems) {
                if ((cI.getCartItemId() == cartItemId) && (cI.getCartItemQuantity() == 1)) {
                    cart.setTotalAmount(cart.getTotalAmount() - cI.getCartItemPrice());
                    cartItems.remove(cI);
                    cartItemRepository.deleteById(cartItemId);
                    break;
                }
                if ((cI.getCartItemId() == cartItemId) && (cI.getCartItemQuantity() > 1)) {
                    cart.setTotalAmount(cart.getTotalAmount() - cI.getCartItemPrice());
                    cI.setCartItemQuantity(cI.getCartItemQuantity() - 1);
                    break;
                }
            }
        } catch (Exception e) {
            throw new CartItemDoesNotExistsException("Cart item does not exists");
        }
        cart.setCartItems(cartItems);
        return cartRepository.save(cart);
    }

    @Override
    @Transactional
    public Cart removeAllFromCart(Long cartId) throws CartNotFoundException {
        try {
            Cart cart = cartRepository.findById(cartId).get();
            List<CartItem> cartItems = cart.getCartItems();
            cartItems.clear();
            cart.setCartItems(cartItems);
            cart.setTotalAmount(0.0);
            cartItemRepository.deleteAllByCart(cart);
            return cartRepository.save(cart);
        } catch (Exception e) {
            throw new CartNotFoundException("Cart id does not exists");
        }
    }

}
