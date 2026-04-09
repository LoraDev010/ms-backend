package com.ms.payments_service.payment.application.port.in;

import java.math.BigDecimal;
import java.util.UUID;

public record AuthorizePaymentCommand(UUID orderId, BigDecimal amount, String currency) {}
