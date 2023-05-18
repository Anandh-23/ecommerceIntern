package com.intern.ecommerce.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long productId;
    @NotBlank(message = "Add name to continue")
    private String productName;
    private Double price;
    private String category;
    private Integer stock;

}
