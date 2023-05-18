package com.intern.ecommerce.service;

import com.intern.ecommerce.entity.Product;
import com.intern.ecommerce.exception.ProductNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ProductService {
    public Product saveProduct(Product product);

    public List<Product> allProducts();

    public Product findProductById(Long productId) throws ProductNotFoundException;

    public Product updateProduct(Long productId, Product product);

    public void deleteProductById(Long productId);

    public List<Product> getProductByCategory(String category);

    public List<Product> getProductByPrice(Long price);
}
