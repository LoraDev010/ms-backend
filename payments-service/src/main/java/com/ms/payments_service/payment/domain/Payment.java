package com.ms.payments_service.payment.domain;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public final class Payment {
  private final UUID id;
  private final UUID orderId;
  private final BigDecimal amount;
  private final String currency;
  private final PaymentStatus status;
  private final Instant createdAt;

  private Payment(UUID id, UUID orderId, BigDecimal amount, String currency, PaymentStatus status, Instant createdAt) {
    this.id = id;
    this.orderId = orderId;
    this.amount = amount;
    this.currency = currency;
    this.status = status;
    this.createdAt = createdAt;
  }

  public static Payment authorizeNew(UUID id, UUID orderId, BigDecimal amount, String currency, Instant now) {
    return new Payment(id, orderId, amount, currency, PaymentStatus.AUTHORIZED, now);
}

  public UUID id() { return id; }
  public UUID orderId() { return orderId; }
  public BigDecimal amount() { return amount; }
  public String currency() { return currency; }
  public PaymentStatus status() { return status; }
  public Instant createdAt() { return createdAt; }
}
