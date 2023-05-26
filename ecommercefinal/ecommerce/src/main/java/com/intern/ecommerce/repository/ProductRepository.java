package com.intern.ecommerce.repository;

import com.intern.ecommerce.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    public List<Product> findAllByCategory(String category);

    public List<Product> findAllByPrice(Long price);
}
