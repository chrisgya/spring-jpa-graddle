package com.chrisgya.springjpagraddle.hateoas;

import com.chrisgya.springjpagraddle.controller.CardController;
import com.chrisgya.springjpagraddle.entity.CardEntity;
import com.chrisgya.springjpagraddle.model.Card;
import org.springframework.beans.BeanUtils;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toList;

@Component
public class CardRepresentationModelAssembler extends
        RepresentationModelAssemblerSupport<CardEntity, Card> {

  /**
   * Creates a new {@link RepresentationModelAssemblerSupport} using the given com.chrisgya.springjpagraddle.controller class and
   * resource type.
   */
  public CardRepresentationModelAssembler() {
    super(CardController.class, Card.class);
  }

  /**
   * Coverts the Card entity to resource
   * @param entity
   * @return
   */
  @Override
  public Card toModel(CardEntity entity) {
    String uid = Objects.nonNull(entity.getUser()) ? entity.getUser().getId().toString() : null;
    Card resource = createModelWithId(entity.getId(), entity);
    BeanUtils.copyProperties(entity, resource);
    resource.setId(entity.getId().toString()); //..setCardNumber(entity.getNumber())
    resource.setCardNumber(entity.getNumber());
    resource.setCvv(entity.getCvv());
    resource.setExpires(entity.getExpires());
    resource.setUserId(uid);
    return resource;
  }

  /**
   * Coverts the collection of Product entities to list of resources.
   * @param entities
   * @return
   */
  public List<Card> toListModel(Iterable<CardEntity> entities) {
    if (Objects.isNull(entities)) return Collections.emptyList();
    return StreamSupport.stream(entities.spliterator(), false).map(e -> toModel(e)).collect(toList());
  }

}
