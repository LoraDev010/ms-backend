package com.ms.orders_service.order.adapter.outbound.http;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.math.BigDecimal;
import java.util.UUID;

@FeignClient(name = "paymentsClient", url = "${orders.clients.payments.base-url}")
public interface PaymentsFeignClient {

  @PostMapping("/authorize")
  AuthResponse authorize(@RequestBody AuthRequest request);

  record AuthRequest(UUID orderId, BigDecimal amount, String currency) {}
  record AuthResponse(UUID paymentId, String status) {}
}
