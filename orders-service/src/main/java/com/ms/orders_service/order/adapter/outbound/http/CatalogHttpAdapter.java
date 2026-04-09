package com.ms.orders_service.order.adapter.outbound.http;

import com.ms.orders_service.order.application.port.out.CatalogQueryPort;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CatalogHttpAdapter implements CatalogQueryPort {

  private final CatalogFeignClient client;

  public CatalogHttpAdapter(CatalogFeignClient client) {
    this.client = client;
  }

  @Override
  public ProductSnapshot getProduct(UUID productId) {
    var dto = client.getProduct(productId);
    return new ProductSnapshot(dto.id(), dto.price(), dto.currency());
  }
}
