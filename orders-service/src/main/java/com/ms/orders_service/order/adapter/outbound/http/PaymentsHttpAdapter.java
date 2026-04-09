package com.ms.orders_service.order.adapter.outbound.http;

import com.ms.orders_service.order.application.port.out.PaymentsPort;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.UUID;

@Component
public class PaymentsHttpAdapter implements PaymentsPort {

  private final PaymentsFeignClient client;

  public PaymentsHttpAdapter(PaymentsFeignClient client) {
    this.client = client;
  }

  @Override
  public PaymentAuthResult authorize(UUID orderId, BigDecimal amount, String currency) {
    var resp = client.authorize(new PaymentsFeignClient.AuthRequest(orderId, amount, currency));
    return new PaymentAuthResult(resp.paymentId(), resp.status());
  }
}
