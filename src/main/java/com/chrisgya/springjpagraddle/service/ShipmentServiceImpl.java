package com.chrisgya.springjpagraddle.service;


import com.chrisgya.springjpagraddle.entity.ShipmentEntity;
import com.chrisgya.springjpagraddle.repository.ShipmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ShipmentServiceImpl implements ShipmentService {

  private ShipmentRepository repository;

  public ShipmentServiceImpl(ShipmentRepository repository) {
    this.repository = repository;
  }

  @Override
  public Iterable<ShipmentEntity> getShipmentByOrderId(String id) {
    return repository.findAllById(List.of(UUID.fromString(id)));
  }
}
