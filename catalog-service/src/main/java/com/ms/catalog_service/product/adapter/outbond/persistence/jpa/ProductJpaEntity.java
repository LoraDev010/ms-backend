package com.ms.catalog_service.product.adapter.outbond.persistence.jpa;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "products")
public class ProductJpaEntity {
  @Id
  private UUID id;

  private String name;

  private BigDecimal price;

  private String currency;

  protected ProductJpaEntity() {}

  public UUID getId() { return id; }
  public String getName() { return name; }
  public BigDecimal getPrice() { return price; }
  public String getCurrency() { return currency; }
}