package com.chrisgya.springjpagraddle.repository;

import com.chrisgya.springjpagraddle.entity.TagEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface TagRepository extends CrudRepository<TagEntity, UUID> {
}
