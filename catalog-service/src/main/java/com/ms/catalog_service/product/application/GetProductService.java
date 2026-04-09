package com.ms.catalog_service.product.application;

import com.ms.catalog_service.product.application.port.in.GetProductUseCase;
import com.ms.catalog_service.product.application.port.out.ProductRepositoryPort;
import com.ms.catalog_service.product.domain.Product;

import java.util.UUID;

public class GetProductService implements GetProductUseCase {
  private final ProductRepositoryPort repo;

  public GetProductService(ProductRepositoryPort repo) {
    this.repo = repo;
  }

  @Override
  public Product getById(UUID id) {
    return repo.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
  }
}