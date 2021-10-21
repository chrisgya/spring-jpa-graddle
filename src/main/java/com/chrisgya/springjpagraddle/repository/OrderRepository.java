package com.chrisgya.springjpagraddle.repository;


import com.chrisgya.springjpagraddle.entity.OrderEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrderRepository extends CrudRepository<OrderEntity, UUID>, OrderRepositoryExt {

  @Query("select o from OrderEntity o join o.userEntity u where u.id = :customerId")
  public Iterable<OrderEntity> findByCustomerId(@Param("customerId") UUID customerId);
}

