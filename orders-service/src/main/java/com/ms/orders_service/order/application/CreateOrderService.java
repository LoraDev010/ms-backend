package com.ms.orders_service.order.application;

import com.ms.orders_service.order.application.port.in.CreateOrderCommand;
import com.ms.orders_service.order.application.port.in.CreateOrderUseCase;
import com.ms.orders_service.order.application.port.out.CatalogQueryPort;
import com.ms.orders_service.order.application.port.out.OrderRepositoryPort;
import com.ms.orders_service.order.application.port.out.PaymentsPort;
import com.ms.orders_service.order.domain.Order;

import java.math.BigDecimal;
import java.time.Clock;
import java.util.UUID;

public class CreateOrderService implements CreateOrderUseCase {

  private final OrderRepositoryPort orderRepositoryPort;
  private final CatalogQueryPort catalogQueryPort;
  private final PaymentsPort paymentsPort;
  private final Clock clock;

  public CreateOrderService(OrderRepositoryPort orderRepositoryPort, CatalogQueryPort catalogQueryPort, PaymentsPort paymentsPort, Clock clock) {
    this.orderRepositoryPort = orderRepositoryPort;
    this.catalogQueryPort = catalogQueryPort;
    this.paymentsPort = paymentsPort;
    this.clock = clock;
  }

  @Override
  public UUID create(CreateOrderCommand command) {
    var product = catalogQueryPort.getProduct(command.productId());

    BigDecimal total = product.price().multiply(BigDecimal.valueOf(command.quantity()));
    UUID orderId = UUID.randomUUID();

    // authorize payment (sync)
    paymentsPort.authorize(orderId, total, product.currency());

    Order order = Order.createNew(orderId, product.id(), command.quantity(), total, product.currency(), clock.instant());
    orderRepositoryPort.save(order);

    return orderId;
  }
}
