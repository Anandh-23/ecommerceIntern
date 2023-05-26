package com.intern.ecommerce.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderItem;

    @ManyToOne
    @JoinColumn(name="orders_product_id",referencedColumnName = "productId")
    private Product product;

    private Integer quantity;

    private Double amount;

}
