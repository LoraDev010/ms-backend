package com.ms.catalog_service.product.application.port.in;

import com.ms.catalog_service.product.domain.Product;
import java.util.UUID;

public interface GetProductUseCase {
  Product getById(UUID id);
}
