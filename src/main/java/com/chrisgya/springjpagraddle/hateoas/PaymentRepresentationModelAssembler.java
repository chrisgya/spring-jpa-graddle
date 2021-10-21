package com.chrisgya.springjpagraddle.hateoas;


import com.chrisgya.springjpagraddle.controller.PaymentController;
import com.chrisgya.springjpagraddle.entity.PaymentEntity;
import com.chrisgya.springjpagraddle.model.Payment;
import org.springframework.beans.BeanUtils;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toList;

@Component
public class PaymentRepresentationModelAssembler extends
        RepresentationModelAssemblerSupport<PaymentEntity, Payment> {

  /**
   * Creates a new {@link RepresentationModelAssemblerSupport} using the given com.chrisgya.springjpagraddle.controller class and
   * resource type.
   */
  public PaymentRepresentationModelAssembler() {
    super(PaymentController.class, Payment.class);
  }

  /**
   * Coverts the Payment entity to resource
   *
   * @param entity
   */
  @Override
  public Payment toModel(PaymentEntity entity) {
    Payment resource = createModelWithId(entity.getId(), entity);
    BeanUtils.copyProperties(entity, resource);
    resource.setId(entity.getId().toString());
    return resource;
  }

  /**
   * Coverts the collection of Product entities to list of resources.
   *
   * @param entities
   */
  public List<Payment> toListModel(Iterable<PaymentEntity> entities) {
    if (Objects.isNull(entities)) {
      return Collections.emptyList();
    }
    return StreamSupport.stream(entities.spliterator(), false).map(e -> toModel(e))
        .collect(toList());
  }

}
