package com.lydiasystems.challenge.controllers;


import com.lydiasystems.challenge.service.PaymentSimulationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("simulation")
@RequiredArgsConstructor
public class PaymentSimulationRestController {

    private final PaymentSimulationService paymentSimulationService;

    @PostMapping("")
    public ResponseEntity<?> paySimulation() {
        paymentSimulationService.simulatePayment(10);
        return ResponseEntity.ok().build();
    }
}