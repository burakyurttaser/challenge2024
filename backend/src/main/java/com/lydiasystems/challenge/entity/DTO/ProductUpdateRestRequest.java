package com.lydiasystems.challenge.entity.DTO;

import com.lydiasystems.challenge.entity.Product;
import lombok.Data;

@Data
public class ProductUpdateRestRequest {
    private Long productId;
    private String productName;
    private double productPrice;
    private int productStock;


    public static Product buildProduct(ProductUpdateRestRequest productUpdateRestRequest) {
        final Product product = new Product();
        product.setProductId(productUpdateRestRequest.getProductId());
        if (productUpdateRestRequest.getProductName() != null) {
            product.setProductName(productUpdateRestRequest.getProductName());
        }

        final Double price = productUpdateRestRequest.getProductPrice();
        final Integer stock = productUpdateRestRequest.getProductStock();
        if (price != null) {
            product.setProductPrice(price);
        }

        if (stock != null) {
            product.setProductStock(stock);
        }
        return product;
    }
}