package com.ms.catalog_service.product.adapter.outbond.persistence.jpa;

import com.ms.catalog_service.product.application.port.out.ProductRepositoryPort;
import com.ms.catalog_service.product.domain.Product;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class ProductPersistenceAdapter implements ProductRepositoryPort {

  private final ProductJpaRepository repo;

  public ProductPersistenceAdapter(ProductJpaRepository repo) {
    this.repo = repo;
  }

  @Override
  public Optional<Product> findById(UUID id) {
    return repo.findById(id).map(e ->
      Product.restore(e.getId(), e.getName(), e.getPrice(), e.getCurrency())
    );
  }
}
