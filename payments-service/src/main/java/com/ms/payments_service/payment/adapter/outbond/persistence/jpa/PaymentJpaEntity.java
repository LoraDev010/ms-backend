package com.ms.payments_service.payment.adapter.outbond.persistence.jpa;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "payments")
public class PaymentJpaEntity {
  @Id
  private UUID id;

  @Column(name = "order_id", nullable = false)
  private UUID orderId;

  private BigDecimal amount;
  private String currency;
  private String status;

  @Column(name = "created_at", nullable = false)
  private Instant createdAt;

  protected PaymentJpaEntity() {}

  public PaymentJpaEntity(UUID id, UUID orderId, BigDecimal amount, String currency, String status, Instant createdAt) {
    this.id = id;
    this.orderId = orderId;
    this.amount = amount;
    this.currency = currency;
    this.status = status;
    this.createdAt = createdAt;
  }
}

