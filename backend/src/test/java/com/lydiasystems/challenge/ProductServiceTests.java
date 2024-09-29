package com.lydiasystems.challenge;

import com.lydiasystems.challenge.entity.Product;
import com.lydiasystems.challenge.repository.ProductRepository;
import com.lydiasystems.challenge.service.ProductServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;

import java.util.Optional;

public class ProductServiceTests {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private Logger logger;

    @InjectMocks
    private ProductServiceImpl productService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void testAddProduct() {
        final Product product = new Product();
        product.setProductId(1L);
        product.setProductName("Test");

        Mockito.when(productRepository.save(product)).thenReturn(product);
        productService.addProduct(product);
        Mockito.verify(productRepository, Mockito.times(1)).save(product);
    }


    @Test
    public void testGetProduct() {
        final Product product = new Product();
        product.setProductId(1L);
        Mockito.when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        final Product product1 = productService.getProductById(1L);
        Assertions.assertEquals(product, product1);
        Assertions.assertEquals(1L, product1.getProductId());
    }

     @Test
    public void testUpdateProduct() {
        final Product product = new Product();
        product.setProductId(5L);
        Mockito.when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        final Product newProduct = new Product();
        newProduct.setProductId(5L);
        newProduct.setProductName("TestUpdated2311");


        productService.updateProduct(newProduct);

        Mockito.verify(productRepository, Mockito.times(1)).save(newProduct);
    }


    @Test
    public void testDeleteProduct() {
        productService.deleteProductById(1L);
        Mockito.verify(productRepository, Mockito.times(1)).deleteById(1L);
    }
}
