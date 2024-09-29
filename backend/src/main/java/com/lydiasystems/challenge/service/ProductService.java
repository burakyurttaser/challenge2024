package com.lydiasystems.challenge.service;

import com.lydiasystems.challenge.entity.Product;

import java.util.List;

public interface ProductService {
    void addProduct(Product product);
    Product getProductById(Long productId);
    List<Product> getAllProducts();
    void updateProduct(Product product);
    void deleteProductById(Long productId);
}