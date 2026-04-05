package com.ms.orders_service.order.application.port.in;

import com.ms.orders_service.order.domain.Order;

import java.util.UUID;

public interface GetOrderUseCase {
    Order getById(UUID id);
}
