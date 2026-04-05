package com.ms.orders_service.order.adapter.inbound.rest;

import java.util.UUID;
import java.time.Instant;

public record OrderResponse(UUID id, String status, Instant createdAt) {
    
}
