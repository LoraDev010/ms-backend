package com.ms.orders_service.order.domain;

import java.time.Instant;
import java.util.UUID;

public class Order {
    private final UUID id;
    private final OrderStatus status;
    private final Instant createdAt;

    private Order(UUID id, OrderStatus status, Instant createdAt) {
        this.id = id;
        this.status = status;
        this.createdAt = createdAt;
    }

    public static Order createNew(UUID id, Instant now) {
        return new Order(id, OrderStatus.CREATED, now);
    }

    public static Order restore(UUID id, OrderStatus status, Instant createdAt) {
        return new Order(id, status, createdAt);
    }


    public UUID id() { return id; }
    public OrderStatus status() { return status; }
    public Instant createdAt() { return createdAt; }
}
