package com.ms.payments_service.payment.adapter.inbound.rest;

import java.math.BigDecimal;
import java.util.UUID;

public record AuthorizePaymentRequest(UUID orderId, BigDecimal amount, String currency) {}
