package com.ms.catalog_service.product.application;

import java.util.UUID;

public class ProductNotFoundException extends RuntimeException {
  public ProductNotFoundException(UUID id) {
    super("Product not found: " + id);
  }
}
