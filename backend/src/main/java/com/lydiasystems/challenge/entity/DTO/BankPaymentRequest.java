package com.lydiasystems.challenge.entity.DTO;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class BankPaymentRequest {
    private BigDecimal price;
}
