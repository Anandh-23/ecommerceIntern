package com.intern.ecommerce.controller;

import com.intern.ecommerce.entity.Cart;
import com.intern.ecommerce.entity.Orders;
import com.intern.ecommerce.exception.InsufficientBalanceException;
import com.intern.ecommerce.exception.OrderNotFoundException;
import com.intern.ecommerce.repository.CustomerRepository;
import com.intern.ecommerce.service.CartService;
import com.intern.ecommerce.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class OrdersController {
    @Autowired
    private OrdersService ordersService;

    @Autowired
    private CartService cartService;

    @GetMapping("/getOrderById/{orderId}")
    public Orders getOrderById(@PathVariable Long orderId) throws Exception {
        return ordersService.getOrdersById(orderId);
    }

    @PostMapping("/addOrder")
    public Orders addOrder(@RequestBody Cart cart) throws Exception {
        Optional<Cart> savedCart = cartService.getCartByCustomerId(cart.getCustomer().getCustomerId());
        Orders savedOrder;
        if (savedCart.isPresent()){
            System.out.println("savedCart = " + savedCart);
            savedOrder = ordersService.addOrder(savedCart.get());
        } else {
            savedOrder = ordersService.addOrder(cartService.addToCart(cart));
        }
        cartService.deleteCartByCustomerCustomerId(savedOrder.getCustomer().getCustomerId());
        return savedOrder;
    }
}
