package com.ms.orders_service.order.adapter.inbound.rest;

import com.ms.orders_service.order.application.port.in.CreateOrderUseCase;
import com.ms.orders_service.order.application.port.in.GetOrderUseCase;
import com.ms.orders_service.order.domain.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class OrdersController {

  private final CreateOrderUseCase createOrderUseCase;
  private final GetOrderUseCase getOrderUseCase;

  public OrdersController(CreateOrderUseCase createOrderUseCase, GetOrderUseCase getOrderUseCase) {
    this.createOrderUseCase = createOrderUseCase;
    this.getOrderUseCase = getOrderUseCase;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public CreateOrderResponse create() {
    UUID id = createOrderUseCase.create();
    return new CreateOrderResponse(id);
  }

  @GetMapping("/{id}")
  public OrderResponse get(@PathVariable UUID id) {
    Order order = getOrderUseCase.getById(id);
    return new OrderResponse(order.id(), order.status().name(), order.createdAt());
  }
}

