package com.lydiasystems.challenge;

import com.lydiasystems.challenge.entity.DTO.BankPaymentRequest;
import com.lydiasystems.challenge.entity.DTO.BankPaymentResponse;
import com.lydiasystems.challenge.entity.Payment;
import com.lydiasystems.challenge.entity.Product;
import com.lydiasystems.challenge.fault.AppException;
import com.lydiasystems.challenge.repository.PaymentRepository;
import com.lydiasystems.challenge.service.BankService;
import com.lydiasystems.challenge.service.PaymentService;
import com.lydiasystems.challenge.service.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;

import static org.mockito.ArgumentMatchers.any;

public class PaymentServiceTests {

    @Mock
    private BankService bankService;

    @Mock
    private PaymentRepository paymentRepository;

    @Mock
    private ProductService productService;

    @Mock
    private Logger logger;

    @InjectMocks
    private PaymentService paymentService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void paySuccess() {
        final Product product = new Product();
        product.setProductId(5L);
        product.setProductPrice(400);
        product.setProductStock(497);

        Mockito.when(productService.getProductById(5L)).thenReturn(product);
        Mockito.when(bankService.pay(any(BankPaymentRequest.class))).thenReturn(new BankPaymentResponse("success"));

        paymentService.pay(5L);

        Mockito.verify(productService, Mockito.times(1)).getProductById(5L);
        Mockito.verify(bankService, Mockito.times(1)).pay(any(BankPaymentRequest.class));
        Mockito.verify(productService, Mockito.times(1)).updateProduct(product);
        Mockito.verify(paymentRepository, Mockito.times(1)).save(any(Payment.class));
    }

    @Test
    public void testPayInvalid(){
        final Product product = new Product();
        product.setProductId(1L);
        product.setProductPrice(700);
        product.setProductStock(497);

        Mockito.when(productService.getProductById(1L)).thenReturn(product);

        Assertions.assertThrows(AppException.class, () -> {
            paymentService.pay(1L);
        });

        Mockito.verify(productService, Mockito.times(1)).getProductById(1L);

        Mockito.verify(bankService, Mockito.never()).pay(any(BankPaymentRequest.class));
        Mockito.verify(paymentRepository, Mockito.never()).save(any(Payment.class));
    }
}