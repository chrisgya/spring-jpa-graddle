package com.chrisgya.springjpagraddle.hateoas;

import com.chrisgya.springjpagraddle.controller.ShipmentController;
import com.chrisgya.springjpagraddle.entity.ShipmentEntity;
import com.chrisgya.springjpagraddle.model.Shipment;
import org.springframework.beans.BeanUtils;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toList;

@Component
public class ShipmentRepresentationModelAssembler extends
        RepresentationModelAssemblerSupport<ShipmentEntity, Shipment> {

  /**
   * Creates a new {@link RepresentationModelAssemblerSupport} using the given com.chrisgya.springjpagraddle.controller class and
   * resource type.
   */
  public ShipmentRepresentationModelAssembler() {
    super(ShipmentController.class, Shipment.class);
  }

  /**
   * Coverts the Shipment entity to resource
   * @param entity
   * @return
   */
  @Override
  public Shipment toModel(ShipmentEntity entity) {
    Shipment resource = createModelWithId(entity.getId(), entity);
    BeanUtils.copyProperties(entity, resource);
    resource.setId(entity.getId().toString());
    return resource;
  }

  /**
   * Coverts the collection of Product entities to list of resources.
   * @param entities
   * @return
   */
  public List<Shipment> toListModel(Iterable<ShipmentEntity> entities) {
    if (Objects.isNull(entities)) return Collections.emptyList();
    return StreamSupport.stream(entities.spliterator(), false).map(e -> toModel(e)).collect(toList());
  }

}
