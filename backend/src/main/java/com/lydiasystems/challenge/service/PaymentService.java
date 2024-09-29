package com.lydiasystems.challenge.service;

import com.lydiasystems.challenge.constants.ErrorConstants;
import com.lydiasystems.challenge.entity.DTO.BankPaymentRequest;
import com.lydiasystems.challenge.entity.DTO.BankPaymentResponse;
import com.lydiasystems.challenge.entity.Payment;
import com.lydiasystems.challenge.entity.Product;
import com.lydiasystems.challenge.fault.AppException;
import com.lydiasystems.challenge.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ConcurrentModificationException;
import java.util.Objects;

@Service
@Transactional(Transactional.TxType.REQUIRES_NEW)
@RequiredArgsConstructor
public class PaymentService {

    private Logger logger = LoggerFactory.getLogger(PaymentService.class);

    private final BankService bankService;
    private final PaymentRepository paymentRepository;
    private final ProductService productService;


    @Async
    public void pay(Long productId) {
        try {
            final Product product = productService.getProductById(productId);
            if (!productBuyingValidation(product)) {
                throw new AppException(ErrorConstants.ERROR_SERVER_RESPONSE_CODE, ErrorConstants.ERROR_PAYMENT_PRODUCT);
            }

            final BankPaymentRequest request = new BankPaymentRequest();
            request.setPrice(new BigDecimal(product.getProductPrice()));

            final BankPaymentResponse response = bankService.pay(request);

            product.setProductStock(product.getProductStock() - 1);
            productService.updateProduct(product);

            final Payment payment = new Payment();
            payment.setBankResponse(response.getResultCode());
            payment.setPrice(new BigDecimal(product.getProductPrice()));
            payment.setProductId(product.getProductId());

            paymentRepository.save(payment);
            logger.info("Payment saved successfully!");
        } catch (OptimisticLockingFailureException e) {
            throw new ConcurrentModificationException(ErrorConstants.ERROR_PAYMENT_PRODUCT_CONCURRENT_EXC);
        }
    }

    private static boolean productBuyingValidation(Product product) {
        if (Objects.isNull(product)) {
            return false;
        }
        if (product.getProductStock() <= 0 || product.getProductPrice() > product.getProductStock()) {
            return false;
        }

        return true;
    }
}
