package com.ms.catalog_service.product.application.port.out;

import com.ms.catalog_service.product.domain.Product;

import java.util.Optional;
import java.util.UUID;

public interface ProductRepositoryPort {
  Optional<Product> findById(UUID id);
}
