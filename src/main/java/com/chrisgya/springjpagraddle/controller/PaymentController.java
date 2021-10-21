package com.chrisgya.springjpagraddle.controller;


import com.chrisgya.springjpagraddle.PaymentApi;
import com.chrisgya.springjpagraddle.hateoas.PaymentRepresentationModelAssembler;
import com.chrisgya.springjpagraddle.model.Authorization;
import com.chrisgya.springjpagraddle.model.PaymentReq;
import com.chrisgya.springjpagraddle.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
public class PaymentController implements PaymentApi {

  private PaymentService service;
  private final PaymentRepresentationModelAssembler assembler;

  public PaymentController(PaymentService service, PaymentRepresentationModelAssembler assembler) {
    this.service = service;
    this.assembler = assembler;
  }

  @Override
  public ResponseEntity<Authorization> authorize(@Valid PaymentReq paymentReq) {
    return null;
  }

  @Override
  public ResponseEntity<Authorization> getOrdersPaymentAuthorization(
      @NotNull @Valid String id) {
    return null;
  }
}
