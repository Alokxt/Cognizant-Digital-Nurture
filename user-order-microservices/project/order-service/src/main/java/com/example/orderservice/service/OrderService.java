package com.example.orderservice.service;

import com.example.orderservice.client.UserClient;
import com.example.orderservice.dto.OrderRequest;
import com.example.orderservice.dto.OrderResponse;
import com.example.orderservice.dto.UserDTO;
import com.example.orderservice.entity.Order;
import com.example.orderservice.entity.OrderStatus;
import com.example.orderservice.exception.InvalidUserException;
import com.example.orderservice.exception.ResourceNotFoundException;
import com.example.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserClient userClient;

    @Transactional
    public OrderResponse createOrder(OrderRequest request) {

        UserDTO user = userClient.getUserById(request.getUserId());
        if (user == null) {
            throw new InvalidUserException("User not found with id: " + request.getUserId());
        }

        Order order = Order.builder()
                .userId(request.getUserId())
                .productName(request.getProductName())
                .quantity(request.getQuantity())
                .totalAmount(request.getTotalAmount())
                .status(OrderStatus.PLACED)
                .build();

        Order saved = orderRepository.save(order);
        return toResponse(saved, user);
    }

    @Transactional(readOnly = true)
    public OrderResponse getOrderById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + id));


        UserDTO user = safeGetUser(order.getUserId());
        return toResponse(order, user);
    }

    @Transactional(readOnly = true)
    public List<OrderResponse> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(order -> toResponse(order, safeGetUser(order.getUserId())))
                .toList();
    }

    @Transactional(readOnly = true)
    public List<OrderResponse> getOrdersByUserId(Long userId) {
        // Confirms the user exists first, so callers get a clean 400 instead of an empty list
        UserDTO user = userClient.getUserById(userId);
        return orderRepository.findByUserId(userId).stream()
                .map(order -> toResponse(order, user))
                .toList();
    }

    @Transactional
    public OrderResponse updateOrderStatus(Long id, OrderStatus status) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + id));
        order.setStatus(status);
        Order saved = orderRepository.save(order);
        return toResponse(saved, safeGetUser(saved.getUserId()));
    }

    @Transactional
    public void deleteOrder(Long id) {
        if (!orderRepository.existsById(id)) {
            throw new ResourceNotFoundException("Order not found with id: " + id);
        }
        orderRepository.deleteById(id);
    }

    /**
     * Best-effort user fetch for read paths - if user-service is temporarily down,
     * we still return the order data rather than failing the whole request.
     */
    private UserDTO safeGetUser(Long userId) {
        try {
            return userClient.getUserById(userId);
        } catch (Exception ex) {
            log.warn("Could not enrich order with user data for userId={}: {}", userId, ex.getMessage());
            return null;
        }
    }

    private OrderResponse toResponse(Order order, UserDTO user) {
        return OrderResponse.builder()
                .id(order.getId())
                .userId(order.getUserId())
                .productName(order.getProductName())
                .quantity(order.getQuantity())
                .totalAmount(order.getTotalAmount())
                .status(order.getStatus())
                .createdAt(order.getCreatedAt())
                .user(user)
                .build();
    }
}
