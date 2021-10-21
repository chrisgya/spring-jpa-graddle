package com.chrisgya.springjpagraddle.service;

import com.chrisgya.springjpagraddle.entity.ShipmentEntity;

public interface ShipmentService {
  Iterable<ShipmentEntity> getShipmentByOrderId(String id);
}
