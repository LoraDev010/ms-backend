package com.ms.orders_service.order.application;

import com.ms.orders_service.order.application.port.in.GetOrderUseCase;
import com.ms.orders_service.order.application.port.out.OrderRepositoryPort;
import com.ms.orders_service.order.domain.Order;

import java.util.UUID;

public class GetOrderService implements GetOrderUseCase {

  private final OrderRepositoryPort orderRepositoryPort;

  public GetOrderService(OrderRepositoryPort orderRepositoryPort) {
    this.orderRepositoryPort = orderRepositoryPort;
  }

  @Override
  public Order getById(UUID id) {
    return orderRepositoryPort.findById(id)
      .orElseThrow(() -> new OrderNotFoundException(id));
  }
}
