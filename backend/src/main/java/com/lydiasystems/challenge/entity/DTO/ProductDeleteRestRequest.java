package com.lydiasystems.challenge.entity.DTO;

import com.sun.istack.NotNull;
import lombok.Data;

@Data
public class ProductDeleteRestRequest {
    @NotNull
    private Long productId;
}