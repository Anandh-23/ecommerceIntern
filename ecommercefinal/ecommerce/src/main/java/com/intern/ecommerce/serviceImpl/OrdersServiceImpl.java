package com.intern.ecommerce.serviceImpl;

import com.intern.ecommerce.entity.Cart;
import com.intern.ecommerce.entity.CartProduct;
import com.intern.ecommerce.entity.OrderItem;
import com.intern.ecommerce.entity.Orders;
import com.intern.ecommerce.exception.InsufficientBalanceException;
import com.intern.ecommerce.exception.OrderNotFoundException;
import com.intern.ecommerce.exception.OutOfStockException;
import com.intern.ecommerce.repository.CustomerRepository;
import com.intern.ecommerce.repository.OrdersRepository;
import com.intern.ecommerce.repository.ProductRepository;
import com.intern.ecommerce.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private OrdersRepository ordersRepository;

    private CustomerRepository customerRepository;

    private ProductRepository productRepository;
    @Override
    public Orders getOrdersById(Long orderId) throws Exception {
        Orders orderById =  ordersRepository.findById(orderId).get();
        if(orderById==null){
            throw new OrderNotFoundException("Order Not Found");
        }
        return orderById;
    }

    @Override
    public Orders addOrder(Cart cart) throws Exception{
        Double billValue = 0.0;
        List<OrderItem> orderItemList = new ArrayList<>();
        for(CartProduct cartProduct : cart.getCartProducts()){
            OrderItem orderItem = OrderItem.builder()
                    .product(cartProduct.getProduct())
                    .quantity(cartProduct.getQuantity())
                    .amount(cartProduct.getAmount())
                    .build();
            billValue += orderItem.getAmount();
            if(orderItem.getQuantity()> cartProduct.getProduct().getStock())
                throw new OutOfStockException("Out of Stock");
            orderItemList.add(orderItem);
        }
        Orders orders = Orders.builder()
                .date(LocalDate.now().toString())
                .customer(cart.getCustomer())
                .orderItems(orderItemList)
                .totalAmount(billValue).build();
        if(orders.getTotalAmount() > orders.getCustomer().getBalance())
            throw new InsufficientBalanceException("Low Balance");

        return ordersRepository.save(orders);
    }


}
