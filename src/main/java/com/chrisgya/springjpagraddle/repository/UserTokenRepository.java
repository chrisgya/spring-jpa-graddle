package com.chrisgya.springjpagraddle.repository;

import com.chrisgya.springjpagraddle.entity.UserTokenEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserTokenRepository extends CrudRepository<UserTokenEntity, UUID> {

    Optional<UserTokenEntity> findByRefreshToken(String refreshToken);
    Optional<UserTokenEntity> deleteByUserId(UUID userId);

}
