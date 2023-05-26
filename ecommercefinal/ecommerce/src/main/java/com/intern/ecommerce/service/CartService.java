package com.intern.ecommerce.service;

import com.intern.ecommerce.entity.Cart;
import com.intern.ecommerce.exception.CartNotFoundException;

import java.util.Optional;

public interface CartService {
    public Cart getCartById(Long cartId) throws Exception;

    public Cart addToCart(Cart cart);

    public Cart updateCart(Long cartId, Cart cart);

    public String deleteCart(Long cartId);

    public String deleteCartByCustomerCustomerId(Long customerId);

//    Cart addToCart(Long customerId, Cart cart);

    public Optional<Cart> getCartByCustomerId(Long customerId);
}
