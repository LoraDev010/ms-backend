package com.ms.payments_service.payment.application.port.out;

import com.ms.payments_service.payment.domain.Payment;

public interface PaymentRepositoryPort {
  void save(Payment payment);
}
