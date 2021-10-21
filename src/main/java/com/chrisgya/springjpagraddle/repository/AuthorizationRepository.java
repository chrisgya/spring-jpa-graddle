package com.chrisgya.springjpagraddle.repository;


import com.chrisgya.springjpagraddle.entity.AuthorizationEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface AuthorizationRepository extends CrudRepository<AuthorizationEntity, UUID> {
}

