package com.chrisgya.springjpagraddle.hateoas;


import com.chrisgya.springjpagraddle.controller.OrderController;
import com.chrisgya.springjpagraddle.entity.OrderEntity;
import com.chrisgya.springjpagraddle.model.Order;
import com.chrisgya.springjpagraddle.service.ItemService;
import org.springframework.beans.BeanUtils;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.time.ZoneOffset;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toList;

@Component
public class OrderRepresentationModelAssembler extends
        RepresentationModelAssemblerSupport<OrderEntity, Order> {

  private UserRepresentationModelAssembler uAssembler;
  private AddressRepresentationModelAssembler aAssembler;
  private CardRepresentationModelAssembler cAssembler;
  private ShipmentRepresentationModelAssembler sAssembler;
  private ItemService itemService;

  /**
   * Creates a new {@link RepresentationModelAssemblerSupport} using the given com.chrisgya.springjpagraddle.controller class and
   * resource type.
   */
  public OrderRepresentationModelAssembler(UserRepresentationModelAssembler uAssembler,
      AddressRepresentationModelAssembler aAssembler, CardRepresentationModelAssembler cAssembler,
      ShipmentRepresentationModelAssembler sAssembler, ItemService itemService) {
    super(OrderController.class, Order.class);
    this.uAssembler = uAssembler;
    this.aAssembler = aAssembler;
    this.cAssembler = cAssembler;
    this.sAssembler = sAssembler;
    this.itemService = itemService;
  }

  /**
   * Coverts the Order entity to resource
   *
   * @param entity
   */
  @Override
  public Order toModel(OrderEntity entity) {
    Order resource = createModelWithId(entity.getId(), entity);
    BeanUtils.copyProperties(entity, resource);

    resource.setId(entity.getId().toString());
    resource.setCustomer(uAssembler.toModel(entity.getUserEntity()));
    resource.setAddress(aAssembler.toModel(entity.getAddressEntity()));
    resource.setCard(cAssembler.toModel(entity.getCardEntity()));
    resource.setItems(itemService.toModelList(entity.getItems()));
    resource.setDate(entity.getOrderDate().toInstant().atOffset(ZoneOffset.UTC));
    return resource;
  }

  /**
   * Coverts the collection of Product entities to list of resources.
   *
   * @param entities
   */
  public List<Order> toListModel(Iterable<OrderEntity> entities) {
    if (Objects.isNull(entities)) {
      return Collections.emptyList();
    }
    return StreamSupport.stream(entities.spliterator(), false).map(e -> toModel(e))
        .collect(toList());
  }

}
