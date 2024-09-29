package com.lydiasystems.challenge.controllers;


import com.lydiasystems.challenge.entity.DTO.ProductAddRestRequest;
import com.lydiasystems.challenge.entity.DTO.ProductUpdateRestRequest;
import com.lydiasystems.challenge.entity.Product;
import com.lydiasystems.challenge.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("products")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class ProductRestController {
    private final ProductService productService;

    @PostMapping("")
    public ResponseEntity<Product> addProduct(@RequestBody ProductAddRestRequest productAddRestRequest) {
        productService.addProduct(ProductAddRestRequest.buildProduct(productAddRestRequest));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProduct(@PathVariable Long productId) {
        final Product product = productService.getProductById(productId);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping("/list/all")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @PutMapping("/update")
    public ResponseEntity<Product> updateProduct(@RequestBody ProductUpdateRestRequest productUpdateRestRequest) {
        productService.updateProduct(ProductUpdateRestRequest.buildProduct(productUpdateRestRequest));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Product> deleteProduct(@PathVariable Long productId) {
        productService.deleteProductById(productId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}