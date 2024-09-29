package com.lydiasystems.challenge.controllers;

import com.lydiasystems.challenge.service.PaymentServiceClients;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;


@RestController
@RequestMapping("payments")
@RequiredArgsConstructor
public class PaymentRestController {

    private final PaymentServiceClients paymentServiceClients;

    @PostMapping("/{productId}")
    public CompletableFuture<ResponseEntity<String>> pay(@PathVariable Long productId) {
        return paymentServiceClients.call(productId).thenApply(result -> ResponseEntity.ok().body(result));
    }
}