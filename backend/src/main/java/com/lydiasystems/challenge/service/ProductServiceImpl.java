package com.lydiasystems.challenge.service;

import com.lydiasystems.challenge.constants.ErrorConstants;
import com.lydiasystems.challenge.entity.Product;
import com.lydiasystems.challenge.fault.AppException;
import com.lydiasystems.challenge.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(Transactional.TxType.REQUIRES_NEW)
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);


    @Override
    public void addProduct(Product product) {
        try {
            this.productRepository.save(product);
            logger.info("Product added successfully::productId{}", product.getProductId());
        } catch (RuntimeException e) {
            logger.error("Error while adding product{}", e.getMessage());
            throw new AppException(ErrorConstants.ERROR_SERVER_RESPONSE_CODE, ErrorConstants.ERROR_ADDING_PRODUCT_NOT_FOUND);
        }
    }

    @Override
    public Product getProductById(Long productId) {
        return this.productRepository.findById(productId).orElseThrow(() -> new AppException(ErrorConstants.ERROR_SERVER_RESPONSE_CODE, ErrorConstants.ERROR_PRODUCT_NOT_FOUND));
    }

    @Override
    public List<Product> getAllProducts() {
        try {
            return this.productRepository.findAll();
        } catch (RuntimeException e) {
            logger.error(ErrorConstants.ERROR_GET_PRODUCT, e.getMessage());
            throw new AppException(ErrorConstants.ERROR_SERVER_RESPONSE_CODE, ErrorConstants.ERROR_GET_PRODUCT);
        }
    }

    @Override
    public void updateProduct(Product product) {
        try {
            final Product oldProduct = this.productRepository.findById(product.getProductId()).orElseThrow(() -> new AppException(ErrorConstants.ERROR_SERVER_RESPONSE_CODE, ErrorConstants.ERROR_PRODUCT_NOT_FOUND));
            oldProduct.setProductName(product.getProductName());
            oldProduct.setProductPrice(product.getProductPrice());
            oldProduct.setProductStock(product.getProductStock());

            this.productRepository.save(oldProduct);
            logger.info("Product updated successfully::productId{}", product.getProductId());
        } catch (RuntimeException e) {
            logger.error("Error while adding product{}", e.getMessage());
            throw new AppException(ErrorConstants.ERROR_SERVER_RESPONSE_CODE, ErrorConstants.ERROR_UPDATE_PRODUCT);
        }
    }

    @Override
    public void deleteProductById(Long productId) {
        try {
            this.productRepository.deleteById(productId);
            logger.info("Product deleted successfully::productId{}", productId);
        } catch (RuntimeException e) {
            logger.error("Error while adding product{}", e.getMessage());
            throw new AppException(ErrorConstants.ERROR_SERVER_RESPONSE_CODE, ErrorConstants.ERROR_DELETING_PRODUCT);
        }
    }
}