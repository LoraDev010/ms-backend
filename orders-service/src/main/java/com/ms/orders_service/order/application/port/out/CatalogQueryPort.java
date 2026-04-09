package com.ms.orders_service.order.application.port.out;

import java.math.BigDecimal;
import java.util.UUID;

public interface CatalogQueryPort {
  ProductSnapshot getProduct(UUID productId);

  record ProductSnapshot(UUID id, BigDecimal price, String currency) {}
}
