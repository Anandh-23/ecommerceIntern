package com.intern.ecommerce.repository;

import com.intern.ecommerce.entity.CartProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartProductRepository extends JpaRepository<CartProduct,Long> {
}
