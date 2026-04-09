package com.ms.orders_service.order.adapter.outbound.persistence.jpa;

import jakarta.persistence.*;

import java.math.BigDecimal;
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

  @Column(name = "product_id")
  private UUID productId;

  @Column(name = "quantity")
  private Integer quantity;

  @Column(name = "total_amount")
  private BigDecimal totalAmount;

  @Column(name = "currency", length = 3)
  private String currency;

  protected OrderJpaEntity() {}

  public OrderJpaEntity(UUID id, String status, Instant createdAt, UUID productId, Integer quantity, BigDecimal totalAmount, String currency) {
    this.id = id;
    this.status = status;
    this.createdAt = createdAt;
    this.productId = productId;
    this.quantity = quantity;
    this.totalAmount = totalAmount;
    this.currency = currency;
  }

  public UUID getId() { return id; }
  public String getStatus() { return status; }
  public Instant getCreatedAt() { return createdAt; }
  public UUID getProductId() { return productId; }
  public Integer getQuantity() { return quantity; }
  public BigDecimal getTotalAmount() { return totalAmount; }
  public String getCurrency() { return currency; }
}
