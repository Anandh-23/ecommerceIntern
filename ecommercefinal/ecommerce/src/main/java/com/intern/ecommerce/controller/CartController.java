package com.intern.ecommerce.controller;

import com.intern.ecommerce.entity.Cart;
import com.intern.ecommerce.exception.CartNotFoundException;
import com.intern.ecommerce.service.CartProductService;
import com.intern.ecommerce.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @Autowired
    private CartProductService cartProductService;

    @GetMapping("/{cartId}")
    public Cart getCartById(@PathVariable("cartId") Long cartId) throws Exception {
        return cartService.getCartById(cartId);
    }
    @PostMapping("/addToCart")
    public Cart addToCart(@RequestBody Cart cart){
        return cartService.addToCart(cart);
    }
    @PutMapping("/updateCart/{cartId}")
    @Transactional
    public Cart updateCart(@PathVariable("cartId") Long cartId,@RequestBody Cart cart) throws Exception {
        Cart cart1 = cartService.getCartById(cartId);
        cartProductService.deleteCartProductByCartId(cart1.getCartProducts());
//        cart.setCustomer(cart1.getCustomer());
        return cartService.updateCart(cartId, cart);
    }
    @DeleteMapping("/deleteCart/{cartId}")
    public String deleteCart(@PathVariable("cartId") Long cartId){
        cartService.deleteCart(cartId);
        return "Cart Deleted";
    }
}
