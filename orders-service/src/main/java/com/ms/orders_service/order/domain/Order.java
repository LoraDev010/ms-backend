package com.ms.orders_service.order.domain;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public class Order {
  private final UUID id;
  private final UUID productId;
  private final int quantity;
  private final BigDecimal totalAmount;
  private final String currency;
  private final OrderStatus status;
  private final Instant createdAt;

  private Order(UUID id, UUID productId, int quantity, BigDecimal totalAmount, String currency, OrderStatus status, Instant createdAt) {
    this.id = id;
    this.productId = productId;
    this.quantity = quantity;
    this.totalAmount = totalAmount;
    this.currency = currency;
    this.status = status;
    this.createdAt = createdAt;
  }

  public static Order createNew(UUID id, UUID productId, int quantity, BigDecimal totalAmount, String currency, Instant now) {
    return new Order(id, productId, quantity, totalAmount, currency, OrderStatus.PENDING_PAYMENT, now);
  }

  public static Order restore(UUID id, UUID productId, int quantity, BigDecimal totalAmount, String currency, OrderStatus status, Instant createdAt) {
    return new Order(id, productId, quantity, totalAmount, currency, status, createdAt);
  }

  public UUID id() { return id; }
  public UUID productId() { return productId; }
  public int quantity() { return quantity; }
  public BigDecimal totalAmount() { return totalAmount; }
  public String currency() { return currency; }
  public OrderStatus status() { return status; }
  public Instant createdAt() { return createdAt; }
}
