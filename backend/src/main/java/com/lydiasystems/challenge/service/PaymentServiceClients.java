package com.lydiasystems.challenge.service;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class PaymentServiceClients {

    private final PaymentService paymentService;

    @Async
    public CompletableFuture<String> call(Long productId) {
        paymentService.pay(productId);
        return CompletableFuture.completedFuture("success");
    }
}
