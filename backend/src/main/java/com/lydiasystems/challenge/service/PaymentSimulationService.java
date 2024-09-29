package com.lydiasystems.challenge.service;


import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
@RequiredArgsConstructor
public class PaymentSimulationService {

    private final PaymentServiceClients paymentServiceClients;
    private Logger logger = LoggerFactory.getLogger(PaymentSimulationService.class);

    public void simulatePayment(int numberOfCustomers) {
        final ExecutorService executorService = Executors.newFixedThreadPool(10);

        for (int i = 0; i < numberOfCustomers; i++) {
            CompletableFuture.runAsync(() -> {
                try {
                    paymentServiceClients.call(5L).join();
                } catch (Exception e) {
                    logger.error(e.getMessage());
                }
            }, executorService);
        }
        executorService.shutdown();

        while (!executorService.isTerminated()) {
        }
    }
}