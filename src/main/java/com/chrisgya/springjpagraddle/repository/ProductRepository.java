package com.chrisgya.springjpagraddle.repository;

import com.chrisgya.springjpagraddle.entity.ProductEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ProductRepository extends CrudRepository<ProductEntity, UUID> {
}

