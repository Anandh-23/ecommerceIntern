package com.intern.ecommerce.repository;

import com.intern.ecommerce.entity.CustomerOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<CustomerOrder,Long> {
}
