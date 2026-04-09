package com.ms.payments_service.payment.adapter.inbound.rest;

import com.ms.payments_service.payment.application.port.in.AuthorizePaymentCommand;
import com.ms.payments_service.payment.application.port.in.AuthorizePaymentUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class PaymentsController {

  private final AuthorizePaymentUseCase useCase;

  public PaymentsController(AuthorizePaymentUseCase useCase) {
    this.useCase = useCase;
  }

  @PostMapping("/authorize")
  @ResponseStatus(HttpStatus.CREATED)
  public AuthorizePaymentResponse authorize(@RequestBody AuthorizePaymentRequest req) {
    var paymentId = useCase.authorize(new AuthorizePaymentCommand(req.orderId(), req.amount(), req.currency()));
    return new AuthorizePaymentResponse(paymentId, "AUTHORIZED");
  }
}
