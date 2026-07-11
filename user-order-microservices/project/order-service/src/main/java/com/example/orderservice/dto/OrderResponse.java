package com.example.orderservice.dto;

import com.example.orderservice.entity.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {
    private Long id;
    private Long userId;
    private String productName;
    private Integer quantity;
    private BigDecimal totalAmount;
    private OrderStatus status;
    private LocalDateTime createdAt;

    // Enriched data fetched from user-service at read time
    private UserDTO user;
}
