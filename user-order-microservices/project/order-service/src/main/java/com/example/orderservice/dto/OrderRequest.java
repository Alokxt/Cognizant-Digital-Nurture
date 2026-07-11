package com.example.orderservice.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderRequest {

    @NotNull(message = "userId is required")
    private Long userId;

    @NotBlank(message = "productName is required")
    private String productName;

    @NotNull(message = "quantity is required")
    @Min(value = 1, message = "quantity must be at least 1")
    private Integer quantity;

    @NotNull(message = "totalAmount is required")
    @Positive(message = "totalAmount must be positive")
    private BigDecimal totalAmount;
}
