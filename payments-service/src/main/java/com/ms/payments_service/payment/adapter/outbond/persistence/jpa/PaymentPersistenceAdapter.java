package com.ms.payments_service.payment.adapter.outbond.persistence.jpa;

import com.ms.payments_service.payment.application.port.out.PaymentRepositoryPort;
import com.ms.payments_service.payment.domain.Payment;
import org.springframework.stereotype.Component;

@Component
public class PaymentPersistenceAdapter implements PaymentRepositoryPort {

  private final PaymentJpaRepository repo;

  public PaymentPersistenceAdapter(PaymentJpaRepository repo) {
    this.repo = repo;
  }

  @Override
  public void save(Payment p) {
    repo.save(new PaymentJpaEntity(
      p.id(), p.orderId(), p.amount(), p.currency(), p.status().name(), p.createdAt()
    ));
  }
}
