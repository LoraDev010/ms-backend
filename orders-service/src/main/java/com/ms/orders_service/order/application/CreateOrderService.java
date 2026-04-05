package com.ms.orders_service.order.application;

import com.ms.orders_service.order.application.port.in.CreateOrderUseCase;
import com.ms.orders_service.order.application.port.out.OrderRepositoryPort;
import com.ms.orders_service.order.domain.Order;

import java.time.Clock;
import java.time.Instant;
import java.util.UUID;

public class CreateOrderService implements CreateOrderUseCase {

  private final OrderRepositoryPort orderRepositoryPort;
  private final Clock clock;

  public CreateOrderService(OrderRepositoryPort orderRepositoryPort, Clock clock) {
    this.orderRepositoryPort = orderRepositoryPort;
    this.clock = clock;
  }

  @Override
  public UUID create() {
    UUID id = UUID.randomUUID();
    Instant now = clock.instant();
    Order order = Order.createNew(id, now);

    orderRepositoryPort.save(order);
    return id;
  }
}
