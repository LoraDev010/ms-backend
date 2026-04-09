package com.ms.payments_service.payment.application;

import com.ms.payments_service.payment.application.port.in.AuthorizePaymentCommand;
import com.ms.payments_service.payment.application.port.in.AuthorizePaymentUseCase;
import com.ms.payments_service.payment.application.port.out.PaymentRepositoryPort;
import com.ms.payments_service.payment.domain.Payment;

import java.time.Clock;
import java.util.UUID;

public class AuthorizePaymentService implements AuthorizePaymentUseCase {

  private final PaymentRepositoryPort repo;
  private final Clock clock;

  public AuthorizePaymentService(PaymentRepositoryPort repo, Clock clock) {
    this.repo = repo;
    this.clock = clock;
  }

  @Override
  public UUID authorize(AuthorizePaymentCommand command) {
    UUID id = UUID.randomUUID();
    Payment payment = Payment.authorizeNew(id, command.orderId(), command.amount(), command.currency(), clock.instant());
    repo.save(payment);
    return id;
  }
}
