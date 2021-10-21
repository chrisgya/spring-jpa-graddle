package com.chrisgya.springjpagraddle.hateoas;


import com.chrisgya.springjpagraddle.controller.AddressController;
import com.chrisgya.springjpagraddle.entity.AddressEntity;
import com.chrisgya.springjpagraddle.model.Address;
import org.springframework.beans.BeanUtils;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toList;

@Component
public class AddressRepresentationModelAssembler extends
        RepresentationModelAssemblerSupport<AddressEntity, Address> {

  /**
   * Creates a new {@link RepresentationModelAssemblerSupport} using the given com.chrisgya.springjpagraddle.controller class and
   * resource type.
   */
  public AddressRepresentationModelAssembler() {
    super(AddressController.class, Address.class);
  }

  /**
   * Coverts the Address entity to resource
   *
   * @param entity
   */
  @Override
  public Address toModel(AddressEntity entity) {
    Address resource = createModelWithId(entity.getId(), entity);
    BeanUtils.copyProperties(entity, resource);
    resource.setId(entity.getId().toString());
    return resource;
  }

  /**
   * Coverts the collection of Product entities to list of resources.
   *
   * @param entities
   */
  public List<Address> toListModel(Iterable<AddressEntity> entities) {
    if (Objects.isNull(entities)) {
      return Collections.emptyList();
    }
    return StreamSupport.stream(entities.spliterator(), false).map(e -> toModel(e))
        .collect(toList());
  }

}
