package com.chrisgya.springjpagraddle.service;


import com.chrisgya.springjpagraddle.entity.AddressEntity;
import com.chrisgya.springjpagraddle.model.AddAddressReq;

import java.util.Optional;

public interface AddressService {
  public Optional<AddressEntity> createAddress(AddAddressReq addAddressReq);
  public void deleteAddressesById(String id);
  public Optional<AddressEntity> getAddressesById(String id);
  public Iterable<AddressEntity> getAllAddresses();
}
