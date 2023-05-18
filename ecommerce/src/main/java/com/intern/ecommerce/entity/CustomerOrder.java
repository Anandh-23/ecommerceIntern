package com.intern.ecommerce.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderId;

    @OneToOne
    @JoinColumn(name = "cartId")
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    private Long totalAmount;

    private Date date;




}
