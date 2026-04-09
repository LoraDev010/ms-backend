package com.ms.orders_service.order.adapter.outbound.persistence.jpa;

import com.ms.orders_service.order.application.port.out.OrderRepositoryPort;
import com.ms.orders_service.order.domain.Order;
import com.ms.orders_service.order.domain.OrderStatus;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class OrderPersistenceAdapter implements OrderRepositoryPort {

  private final OrderJpaRepository repository;

  public OrderPersistenceAdapter(OrderJpaRepository repository) {
    this.repository = repository;
  }

  @Override
  public void save(Order order) {
    repository.save(new OrderJpaEntity(
      order.id(),
      order.status().name(),
      order.createdAt(),
      order.productId(),
      order.quantity(),
      order.totalAmount(),
      order.currency()
    ));
  }

  @Override
  public Optional<Order> findById(UUID id) {
    return repository.findById(id).map(e ->
      Order.restore(
        e.getId(),
        e.getProductId(),
        e.getQuantity() == null ? 0 : e.getQuantity(),
        e.getTotalAmount(),
        e.getCurrency(),
        OrderStatus.valueOf(e.getStatus()),
        e.getCreatedAt()
      )
    );
  }
}
