package com.ms.catalog_service.product.domain;

import java.math.BigDecimal;
import java.util.UUID;

public class Product {
  private final UUID id;
  private final String name;
  private final BigDecimal price;
  private final String currency;

  private Product(UUID id, String name, BigDecimal price, String currency) {
    this.id = id;
    this.name = name;
    this.price = price;
    this.currency = currency;
  }

  public static Product restore(UUID id, String name, BigDecimal price, String currency) {
    return new Product(id, name, price, currency);
  }

  public UUID id() { return id; }
  public String name() { return name; }
  public BigDecimal price() { return price; }
  public String currency() { return currency; }
}
