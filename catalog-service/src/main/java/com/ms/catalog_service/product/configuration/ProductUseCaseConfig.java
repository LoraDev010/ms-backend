package com.ms.catalog_service.product.configuration;

import com.ms.catalog_service.product.application.GetProductService;
import com.ms.catalog_service.product.application.port.in.GetProductUseCase;
import com.ms.catalog_service.product.application.port.out.ProductRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductUseCaseConfig {
  @Bean
  GetProductUseCase getProductUseCase(ProductRepositoryPort repo) {
    return new GetProductService(repo);
  }
}
