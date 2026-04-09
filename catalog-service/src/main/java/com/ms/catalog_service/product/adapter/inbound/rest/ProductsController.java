package com.ms.catalog_service.product.adapter.inbound.rest;

import com.ms.catalog_service.product.application.port.in.GetProductUseCase;
import com.ms.catalog_service.product.domain.Product;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductsController {

  private final GetProductUseCase getProductUseCase;

  public ProductsController(GetProductUseCase getProductUseCase) {
    this.getProductUseCase = getProductUseCase;
  }

  @GetMapping("/{id}")
  public ProductResponse getById(@PathVariable UUID id) {
    Product p = getProductUseCase.getById(id);
    return new ProductResponse(p.id(), p.name(), p.price(), p.currency());
  }
}
