package com.intern.ecommerce.repository;

import com.intern.ecommerce.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository <Cart,Long> {
    Optional<Cart> findByCustomerCustomerId(Long customerId);
}
