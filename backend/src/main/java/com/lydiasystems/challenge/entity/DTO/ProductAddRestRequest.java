package com.lydiasystems.challenge.entity.DTO;

import com.lydiasystems.challenge.entity.Product;
import com.sun.istack.NotNull;
import lombok.Data;

@Data
public class ProductAddRestRequest {
    @NotNull
    private String productName;
    @NotNull
    private double productPrice;
    @NotNull
    private int productStock;


    public static Product buildProduct(ProductAddRestRequest productAddRestRequest) {
        final Product product = new Product();
        product.setProductName(productAddRestRequest.getProductName());
        product.setProductPrice(productAddRestRequest.getProductPrice());
        product.setProductStock(productAddRestRequest.getProductStock());
        return product;
    }
}