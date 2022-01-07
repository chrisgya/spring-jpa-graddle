package com.chrisgya.springjpagraddle.service;


import com.chrisgya.springjpagraddle.entity.AddressEntity;
import com.chrisgya.springjpagraddle.model.AddAddressReq;

import java.util.Optional;

public interface AddressService {
  Optional<AddressEntity> createAddress(AddAddressReq addAddressReq);
  void deleteAddressesById(String id);
  Optional<AddressEntity> getAddressesById(String id);
  Iterable<AddressEntity> getAllAddresses();
}
