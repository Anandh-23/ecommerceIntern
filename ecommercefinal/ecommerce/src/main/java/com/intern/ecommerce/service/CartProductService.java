package com.intern.ecommerce.service;

import com.intern.ecommerce.entity.CartProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CartProductService {

    void deleteCartProductByCartId(List<CartProduct> cartProducts);

}
