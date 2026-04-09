package com.ms.payments_service.payment.configuration;

import com.ms.payments_service.payment.application.AuthorizePaymentService;
import com.ms.payments_service.payment.application.port.in.AuthorizePaymentUseCase;
import com.ms.payments_service.payment.application.port.out.PaymentRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;

@Configuration
public class PaymentUseCaseConfig {
  @Bean
  AuthorizePaymentUseCase authorizePaymentUseCase(PaymentRepositoryPort repo, Clock clock) {
    return new AuthorizePaymentService(repo, clock);
  }
}
