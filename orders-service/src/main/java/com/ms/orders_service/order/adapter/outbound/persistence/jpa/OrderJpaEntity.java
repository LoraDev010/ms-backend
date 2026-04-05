package com.ms.orders_service.order.adapter.outbound.persistence.jpa;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "orders")
public class OrderJpaEntity {

  @Id
  @Column(name = "id", nullable = false)
  private UUID id;

  @Column(name = "status", nullable = false, length = 32)
  private String status;

  @Column(name = "created_at", nullable = false)
  private Instant createdAt;

  protected OrderJpaEntity() {}

  public OrderJpaEntity(UUID id, String status, Instant createdAt) {
    this.id = id;
    this.status = status;
    this.createdAt = createdAt;
  }

  public UUID getId() { return id; }
  public String getStatus() { return status; }
  public Instant getCreatedAt() { return createdAt; }
}