package com.chrisgya.springjpagraddle.repository;

import com.chrisgya.springjpagraddle.entity.OrderItemEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface OrderItemRepository extends CrudRepository<OrderItemEntity, UUID> {
}
