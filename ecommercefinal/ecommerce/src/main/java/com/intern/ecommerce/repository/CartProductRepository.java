package com.intern.ecommerce.repository;

import com.intern.ecommerce.entity.CartProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartProductRepository extends JpaRepository<CartProduct,Long> {

    @Modifying
    @Query(value = "delete from cart_product c where c.cart_product_id in :cartProductIds", nativeQuery = true)
    void deleteAllByCartId(@Param("cartProductIds") List<Long> cartProductIds);

    CartProduct findByProductProductId(Long productId);

}
