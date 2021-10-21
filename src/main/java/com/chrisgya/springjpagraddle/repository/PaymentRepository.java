package com.chrisgya.springjpagraddle.repository;


import com.chrisgya.springjpagraddle.entity.PaymentEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface PaymentRepository extends CrudRepository<PaymentEntity, UUID> {
}

