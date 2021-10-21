package com.chrisgya.springjpagraddle.hateoas;

import com.chrisgya.springjpagraddle.controller.CustomerController;
import com.chrisgya.springjpagraddle.entity.UserEntity;
import com.chrisgya.springjpagraddle.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toList;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class UserRepresentationModelAssembler extends
        RepresentationModelAssemblerSupport<UserEntity, User> {

  /**
   * Creates a new {@link RepresentationModelAssemblerSupport} using the given com.chrisgya.springjpagraddle.controller class and
   * resource type.
   */
  public UserRepresentationModelAssembler() {
    super(CustomerController.class, User.class);
  }

  /**
   * Coverts the User entity to resource
   *
   * @param entity
   */
  @Override
  public User toModel(UserEntity entity) {
    User resource = createModelWithId(entity.getId(), entity);
    BeanUtils.copyProperties(entity, resource);
    resource.setId(entity.getId().toString());

    resource.add(linkTo(methodOn(CustomerController.class).getAddressesByCustomerId(entity.getId().toString())).withRel("self_addresses"));
    resource.add(linkTo(methodOn(CustomerController.class).getCardByCustomerId(entity.getId().toString())).withRel("self_card"));
    return resource;
  }

  /**
   * Coverts the collection of Product entities to list of resources.
   *
   * @param entities
   */
  public List<User> toListModel(Iterable<UserEntity> entities) {
    if (Objects.isNull(entities)) {
      return Collections.emptyList();
    }

    return StreamSupport.stream(entities.spliterator(), false).map(e -> toModel(e))
        .collect(toList());
  }

}
