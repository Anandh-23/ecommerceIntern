package com.intern.ecommerce.controller;

import com.intern.ecommerce.entity.Product;
import com.intern.ecommerce.exception.ProductNotFoundException;
import com.intern.ecommerce.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class ProductController {
    @Autowired
    private ProductService productService;
    private final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);
    @PostMapping("/addProduct")
    public Product saveProduct(@RequestBody Product product){
        LOGGER.info("Adding Product");
        return productService.saveProduct(product);
    }

    @GetMapping("/products")
    public List<Product> allProducts(){
        return productService.allProducts();
    }

    @GetMapping("/products/{id}")
    public Product findProductById(@PathVariable("id") Long productId) throws ProductNotFoundException {
        return productService.findProductById(productId);
    }

    @PutMapping("/products/{id}")
    public Product updateProduct(@PathVariable("id") Long productId,@RequestBody Product product){
        return productService.updateProduct(productId,product);
    }

    @DeleteMapping("/products/{id}")
    public String deleteProductById(@PathVariable("id")Long productId){
        productService.deleteProductById(productId);
        return "Deleted";
    }

    @GetMapping("/products/category/{category}")
    public List<Product> getProductByCategory(@PathVariable("category") String category){
        return productService.getProductByCategory(category);
    }

    @GetMapping("/products/price/{price}")
    public List<Product> getProductByPrice(@PathVariable("price") Long price){
        return productService.getProductByPrice(price);
    }
}
