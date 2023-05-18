package com.intern.ecommerce.entity;

import jakarta.persistence.*;

@Entity
public class CartProduct{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cartProductId;

    @ManyToOne
    @JoinColumn(name = "cart_id",nullable = false)
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "product_id",nullable = false)
    private Product product;

    private int quantity;







}
