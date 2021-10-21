package com.chrisgya.springjpagraddle.repository;


import com.chrisgya.springjpagraddle.entity.CardEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface CardRepository extends CrudRepository<CardEntity, UUID> {
}

