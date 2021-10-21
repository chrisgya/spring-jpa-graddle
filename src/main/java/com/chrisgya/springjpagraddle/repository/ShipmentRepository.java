package com.chrisgya.springjpagraddle.repository;


import com.chrisgya.springjpagraddle.entity.ShipmentEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ShipmentRepository extends CrudRepository<ShipmentEntity, UUID> {
}

