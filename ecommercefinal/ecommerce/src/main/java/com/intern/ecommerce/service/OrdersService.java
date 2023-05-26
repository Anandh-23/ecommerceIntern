package com.intern.ecommerce.service;

import com.intern.ecommerce.entity.Cart;
import com.intern.ecommerce.entity.Orders;
import com.intern.ecommerce.exception.InsufficientBalanceException;
import com.intern.ecommerce.exception.OrderNotFoundException;
import jakarta.persistence.criteria.Order;

public interface OrdersService {
    public Orders getOrdersById(Long orderId) throws Exception;

    public Orders addOrder(Cart cart) throws Exception;

}
