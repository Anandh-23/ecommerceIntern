package com.intern.ecommerce.serviceImpl;

import com.intern.ecommerce.entity.Cart;
import com.intern.ecommerce.entity.CartProduct;
import com.intern.ecommerce.entity.Customer;
import com.intern.ecommerce.entity.Product;
import com.intern.ecommerce.exception.CartNotFoundException;
import com.intern.ecommerce.repository.CartProductRepository;
import com.intern.ecommerce.repository.CartRepository;
import com.intern.ecommerce.repository.CustomerRepository;
import com.intern.ecommerce.repository.ProductRepository;
import com.intern.ecommerce.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartProductRepository cartProductRepository;
    @Override
    public Cart getCartById(Long cartId) throws Exception {
        Cart cartById =  cartRepository.findById(cartId).get();
        if(cartById == null)
            throw new CartNotFoundException("Cart Not Found");
        return cartById;
    }

    @Override
    public Cart addToCart(Cart cart) {
        Optional<Customer> customer = customerRepository.findById(cart.getCustomer().getCustomerId());
        if(customer.isPresent()){
            cart.setCustomer((customer.get()));
        }

        List<CartProduct> cartProductList = cart.getCartProducts();
        for(CartProduct cartProduct : cartProductList){
            Optional<Product> product = productRepository.findById((cartProduct.getProduct().getProductId()));

            if(product.isPresent()){
                cartProduct.setProduct(product.get());
            }
            cartProduct.setAmount(product.get().getPrice() * cartProduct.getQuantity());
        }

        return cartRepository.save(cart);

    }

    @Override
    public Cart updateCart(Long cartId, Cart cart) {
        Optional<Cart> optionalCart = cartRepository.findById(cartId);
        Cart oldCart = optionalCart.get();
        oldCart.getCartProducts().clear();
        oldCart.getCartProducts().addAll(cart.getCartProducts());
        List<CartProduct> cartProductList = oldCart.getCartProducts();
        for(CartProduct cartProduct : cartProductList){
            Optional<Product> product = productRepository.findById((cartProduct.getProduct().getProductId()));

            if(product.isPresent()){
                cartProduct.setProduct(product.get());
            }
            cartProduct.setAmount(product.get().getPrice() * cartProduct.getQuantity());
        }
        return cartRepository.save(oldCart);
    }

    @Override
    public String deleteCart(Long cartId) {
        cartRepository.deleteById(cartId);
        return "Deleted successfully";
    }

    @Override
    public String deleteCartByCustomerCustomerId(Long customerId) {
        Cart cart = cartRepository.findByCustomerCustomerId(customerId).get();
        cartRepository.deleteById(cart.getCartId());
        return "Deleted Successfully";
    }

    @Override
    public Optional<Cart> getCartByCustomerId(Long customerId) {
        return cartRepository.findByCustomerCustomerId(customerId);
    }
}
