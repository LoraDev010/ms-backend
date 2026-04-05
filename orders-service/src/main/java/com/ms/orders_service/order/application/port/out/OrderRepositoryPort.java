package com.ms.orders_service.order.application.port.out;

import com.ms.orders_service.order.domain.Order;

import java.util.Optional;
import java.util.UUID;

public interface OrderRepositoryPort {
    void save(Order order);
    Optional<Order> findById(UUID id);
}
