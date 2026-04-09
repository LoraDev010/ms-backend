package com.ms.catalog_service.product.adapter.inbound.rest;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductResponse(UUID id, String name, BigDecimal price, String currency) {}
