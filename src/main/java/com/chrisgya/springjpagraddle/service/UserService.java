package com.chrisgya.springjpagraddle.service;


import com.chrisgya.springjpagraddle.entity.AddressEntity;
import com.chrisgya.springjpagraddle.entity.CardEntity;
import com.chrisgya.springjpagraddle.entity.UserEntity;
import com.chrisgya.springjpagraddle.model.RefreshToken;
import com.chrisgya.springjpagraddle.model.SignedInUser;
import com.chrisgya.springjpagraddle.model.User;

import java.util.Optional;

public interface UserService {

  void deleteCustomerById(String id);

  Optional<Iterable<AddressEntity>> getAddressesByCustomerId(String id);

  Iterable<UserEntity> getAllCustomers();

  Optional<CardEntity> getCardByCustomerId(String id);

  Optional<UserEntity> getCustomerById(String id);

  Optional<SignedInUser> createUser(User user);

  UserEntity findUserByUsername(String username);

  SignedInUser getSignedInUser(UserEntity userEntity);

  Optional<SignedInUser> getAccessToken(RefreshToken refreshToken);

  void removeRefreshToken(RefreshToken refreshToken);
}
