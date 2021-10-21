package com.chrisgya.springjpagraddle.repository;


import com.chrisgya.springjpagraddle.entity.AddressEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface AddressRepository extends CrudRepository<AddressEntity, UUID> {
}

