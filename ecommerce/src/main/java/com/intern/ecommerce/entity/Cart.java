package com.intern.ecommerce.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cartId;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id",nullable = false)
    private Customer customer;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    private List<CartProduct> items  = new ArrayList<>();


}
