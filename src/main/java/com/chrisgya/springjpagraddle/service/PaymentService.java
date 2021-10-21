package com.chrisgya.springjpagraddle.service;

import com.chrisgya.springjpagraddle.entity.AuthorizationEntity;
import com.chrisgya.springjpagraddle.model.PaymentReq;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Optional;

public interface PaymentService {

  public Optional<AuthorizationEntity> authorize(@Valid PaymentReq paymentReq);
  public Optional<AuthorizationEntity> getOrdersPaymentAuthorization(@NotNull String orderId);
}
