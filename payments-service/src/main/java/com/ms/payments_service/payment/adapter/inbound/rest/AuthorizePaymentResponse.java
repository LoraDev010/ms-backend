package com.ms.payments_service.payment.adapter.inbound.rest;

import java.util.UUID;

public record AuthorizePaymentResponse(UUID paymentId, String status) {}
