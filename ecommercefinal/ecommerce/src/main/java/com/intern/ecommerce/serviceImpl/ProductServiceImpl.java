package com.intern.ecommerce.serviceImpl;

import com.intern.ecommerce.entity.Product;
import com.intern.ecommerce.exception.ProductNotCreatedException;
import com.intern.ecommerce.exception.ProductNotFoundException;
import com.intern.ecommerce.repository.ProductRepository;
import com.intern.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Override
    public Product saveProduct(Product product) throws ProductNotCreatedException {

        Product savedProduct =  productRepository.save(product);
        if(savedProduct==null)
            throw new ProductNotCreatedException("Product Not Created");
        return savedProduct;
    }

    @Override
    public List<Product> allProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product findProductById(Long productId) throws ProductNotFoundException {
        Optional<Product> product = productRepository.findById(productId);
        if(!product.isPresent()){
            throw new ProductNotFoundException("Product Not Available");
        }
        return product.get();
    }

    @Override
    public Product updateProduct(Long productId, Product product) {
        Product updateproduct = productRepository.findById(productId).get();
        if(Objects.nonNull(product.getProductName()) && !"".equalsIgnoreCase(product.getProductName())){
            updateproduct.setProductName(product.getProductName());
        }
        if(Objects.nonNull(product.getStock())){
            updateproduct.setStock(product.getStock());
        }
        if(Objects.nonNull(product.getPrice())){
            updateproduct.setPrice(product.getPrice());
        }
        if(Objects.nonNull(product.getCategory()) && !"".equalsIgnoreCase(product.getCategory())){
            updateproduct.setCategory(product.getCategory());
        }
        return productRepository.save(updateproduct);
    }

    @Override
    public void deleteProductById(Long productId) {
        productRepository.deleteById(productId);
    }

    @Override
    public List<Product> getProductByCategory(String category) {
        return productRepository.findAllByCategory(category);
    }

    @Override
    public List<Product> getProductByPrice(Long price) {
        return productRepository.findAllByPrice(price);
    }
}
