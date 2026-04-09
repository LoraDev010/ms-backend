package com.ms.orders_service.order.application.port.out;

import java.math.BigDecimal;
import java.util.UUID;

public interface PaymentsPort {
  PaymentAuthResult authorize(UUID orderId, BigDecimal amount, String currency);

  record PaymentAuthResult(UUID paymentId, String status) {}
}
