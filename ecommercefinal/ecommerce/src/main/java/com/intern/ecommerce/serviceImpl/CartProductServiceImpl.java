package com.intern.ecommerce.serviceImpl;

import com.intern.ecommerce.entity.CartProduct;
import com.intern.ecommerce.repository.CartProductRepository;
import com.intern.ecommerce.service.CartProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartProductServiceImpl implements CartProductService {

    @Autowired
    private CartProductRepository cartProductRepository;

    @Override
    public void deleteCartProductByCartId(List<CartProduct> cartProducts) {
        List<Long> cartProductIds = new ArrayList<>();
        for (CartProduct cartProduct: cartProducts) {
            cartProductIds.add(cartProduct.getCartProductId());
        }
        cartProductRepository.deleteAllByCartId(cartProductIds);
    }
}
