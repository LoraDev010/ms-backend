package com.ms.orders_service.order.configuration;

import com.ms.orders_service.order.application.CreateOrderService;
import com.ms.orders_service.order.application.GetOrderService;
import com.ms.orders_service.order.application.port.in.CreateOrderUseCase;
import com.ms.orders_service.order.application.port.in.GetOrderUseCase;
import com.ms.orders_service.order.application.port.out.CatalogQueryPort;
import com.ms.orders_service.order.application.port.out.OrderRepositoryPort;
import com.ms.orders_service.order.application.port.out.PaymentsPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;

@Configuration
public class OrderUseCaseConfig {

  @Bean
  CreateOrderUseCase createOrderUseCase(
    OrderRepositoryPort repo,
    CatalogQueryPort catalog,
    PaymentsPort payments,
    Clock clock
  ) {
    return new CreateOrderService(repo, catalog, payments, clock);
  }

  @Bean
  GetOrderUseCase getOrderUseCase(OrderRepositoryPort repo) {
    return new GetOrderService(repo);
  }
}
