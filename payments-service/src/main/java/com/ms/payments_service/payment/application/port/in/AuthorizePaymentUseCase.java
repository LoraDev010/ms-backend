package com.ms.payments_service.payment.application.port.in;

import java.util.UUID;

public interface AuthorizePaymentUseCase {
  UUID authorize(AuthorizePaymentCommand command);
}
