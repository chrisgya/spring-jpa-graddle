package com.chrisgya.springjpagraddle.service;


import com.chrisgya.springjpagraddle.entity.AddressEntity;
import com.chrisgya.springjpagraddle.entity.CardEntity;
import com.chrisgya.springjpagraddle.entity.UserEntity;
import com.chrisgya.springjpagraddle.entity.UserTokenEntity;
import com.chrisgya.springjpagraddle.exception.GenericAlreadyExistsException;
import com.chrisgya.springjpagraddle.exception.InvalidRefreshTokenException;
import com.chrisgya.springjpagraddle.model.RefreshToken;
import com.chrisgya.springjpagraddle.model.SignedInUser;
import com.chrisgya.springjpagraddle.model.User;
import com.chrisgya.springjpagraddle.repository.UserRepository;
import com.chrisgya.springjpagraddle.repository.UserTokenRepository;
import com.chrisgya.springjpagraddle.security.JwtManager;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class UserServiceImpl implements UserService {

  private final UserRepository repository;
  private final UserTokenRepository userTokenRepository;
  private final PasswordEncoder bCryptPasswordEncoder;
  private final JwtManager tokenManager;

  public UserServiceImpl(UserRepository repository, UserTokenRepository userTokenRepository,
                         PasswordEncoder bCryptPasswordEncoder, JwtManager tokenManager) {
    this.repository = repository;
    this.userTokenRepository = userTokenRepository;
    this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    this.tokenManager = tokenManager;
  }

  @Override
  public void deleteCustomerById(String id) {
    repository.deleteById(UUID.fromString(id));
  }

  @Override
  public Optional<Iterable<AddressEntity>> getAddressesByCustomerId(String id) {
    return repository.findById(UUID.fromString(id)).map(UserEntity::getAddresses);
  }

  @Override
  public Iterable<UserEntity> getAllCustomers() {
    return repository.findAll();
  }

  @Override
  public Optional<CardEntity> getCardByCustomerId(String id) {
    AtomicReference<Optional<CardEntity>> cardEntity = new AtomicReference<>(Optional.empty());
    repository.findById(UUID.fromString(id))
            .ifPresent(ue -> {
              if (Objects.nonNull(ue.getCard()) && !ue.getCard().isEmpty()) {
                cardEntity.set(Optional.of(ue.getCard().get(0)));
              }
            });
    return cardEntity.get();
  }

  @Override
  public Optional<UserEntity> getCustomerById(String id) {
    return repository.findById(UUID.fromString(id));
  }

  @Override
  @Transactional
  public Optional<SignedInUser> createUser(User user) {
    Integer count = repository.findByUsernameOrEmail(user.getUsername(), user.getEmail());
    if (count > 0) {
      throw new GenericAlreadyExistsException("Use different username and email.");
    }
    UserEntity userEntity = repository.save(toEntity(user));
    return Optional.of(createSignedUserWithRefreshToken(userEntity));
  }

  @Override
  @Transactional
  public SignedInUser getSignedInUser(UserEntity userEntity) {
    userTokenRepository.deleteByUserId(userEntity.getId());
    return createSignedUserWithRefreshToken(userEntity);
  }

  private SignedInUser createSignedUserWithRefreshToken(UserEntity userEntity) {
    return createSignedInUser(userEntity).refreshToken(createRefreshToken(userEntity));
  }

  private SignedInUser createSignedInUser(UserEntity userEntity) {
    String token = tokenManager.create(org.springframework.security.core.userdetails.User.builder()
            .username(userEntity.getUsername())
            .password(userEntity.getPassword())
            .authorities(Objects.nonNull(userEntity.getRole()) ? userEntity.getRole().name() : "")
            .build());
    return new SignedInUser().username(userEntity.getUsername()).accessToken(token)
            .userId(userEntity.getId().toString());
  }

  @Override
  public Optional<SignedInUser> getAccessToken(RefreshToken refreshToken) {
    // You may add a validation for time that would remove/invalidate the refresh token
    return userTokenRepository.findByRefreshToken(refreshToken.getRefreshToken())
            .map(ut -> Optional.of(createSignedInUser(ut.getUser()).refreshToken(refreshToken.getRefreshToken())))
            .orElseThrow(() -> new InvalidRefreshTokenException("Invalid token."));
  }

  @Override
  public void removeRefreshToken(RefreshToken refreshToken) {
    userTokenRepository.findByRefreshToken(refreshToken.getRefreshToken())
            .ifPresentOrElse(userTokenRepository::delete, () -> {
              throw new InvalidRefreshTokenException("Invalid token.");
            });
  }

  @Override
  public UserEntity findUserByUsername(String username) {
    if (Strings.isBlank(username)) {
      throw new UsernameNotFoundException("Invalid user.");
    }
    final String uname = username.trim();
    Optional<UserEntity> oUserEntity = repository.findByUsername(uname);
    UserEntity userEntity = oUserEntity.orElseThrow(
            () -> new UsernameNotFoundException(String.format("Given user(%s) not found.", uname)));
    return userEntity;
  }

  private UserEntity toEntity(User user) {
    UserEntity userEntity = new UserEntity();
    BeanUtils.copyProperties(user, userEntity);
    userEntity.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
    return userEntity;
  }

  private String createRefreshToken(UserEntity user) {
    String token = RandomHolder.randomKey(128);
    userTokenRepository.save(new UserTokenEntity().setRefreshToken(token).setUser(user));
    return token;
  }

  // https://stackoverflow.com/a/31214709/109354
  // or can use org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric(n)
  private static class RandomHolder {
    static final Random random = new SecureRandom();
    public static String randomKey(int length) {
      return String.format("%"+length+"s", new BigInteger(length*5/*base 32,2^5*/, random)
              .toString(32)).replace('\u0020', '0');
    }
  }

}
