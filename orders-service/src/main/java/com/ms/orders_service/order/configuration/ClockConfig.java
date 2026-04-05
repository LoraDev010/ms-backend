package com.ms.orders_service.order.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;

@Configuration
public class ClockConfig {
  @Bean
  Clock clock() {
    return Clock.systemUTC();
  }
}

