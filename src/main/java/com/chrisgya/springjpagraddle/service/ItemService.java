package com.chrisgya.springjpagraddle.service;


import com.chrisgya.springjpagraddle.entity.ItemEntity;
import com.chrisgya.springjpagraddle.model.Item;

import java.util.List;

public interface ItemService {

  ItemEntity toEntity(Item m);

  List<ItemEntity> toEntityList(List<Item> items);

  Item toModel(ItemEntity e);

  List<Item> toModelList(List<ItemEntity> items);
}
