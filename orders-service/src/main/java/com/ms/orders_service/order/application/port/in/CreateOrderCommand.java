package com.ms.orders_service.order.application.port.in;

import java.util.UUID;

public record CreateOrderCommand(UUID productId, int quantity) {}
