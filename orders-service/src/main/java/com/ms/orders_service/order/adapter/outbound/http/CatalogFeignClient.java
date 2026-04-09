package com.ms.orders_service.order.adapter.outbound.http;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;
import java.util.UUID;

@FeignClient(name = "catalogClient", url = "${orders.clients.catalog.base-url}")
public interface CatalogFeignClient {

  @GetMapping("/products/{id}")
  ProductDto getProduct(@PathVariable UUID id);

  record ProductDto(UUID id, BigDecimal price, String currency) {}
}
