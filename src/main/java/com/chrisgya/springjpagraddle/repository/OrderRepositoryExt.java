package com.chrisgya.springjpagraddle.repository;


import com.chrisgya.springjpagraddle.entity.OrderEntity;
import com.chrisgya.springjpagraddle.model.NewOrder;

import java.util.Optional;

public interface OrderRepositoryExt {
  Optional<OrderEntity> insert(NewOrder m);
}

