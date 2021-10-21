package com.chrisgya.springjpagraddle.hateoas;

import com.chrisgya.springjpagraddle.controller.ProductController;
import com.chrisgya.springjpagraddle.entity.ProductEntity;
import com.chrisgya.springjpagraddle.model.Product;
import com.chrisgya.springjpagraddle.model.Tag;
import org.springframework.beans.BeanUtils;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toList;

@Component
public class ProductRepresentationModelAssembler extends
        RepresentationModelAssemblerSupport<ProductEntity, Product> {

  /**
   * Creates a new {@link RepresentationModelAssemblerSupport} using the given com.chrisgya.springjpagraddle.controller class and
   * resource type.
   */
  public ProductRepresentationModelAssembler() {
    super(ProductController.class, Product.class);
  }

  /**
   * Coverts the Product entity to resource
   *
   * @param entity
   */
  @Override
  public Product toModel(ProductEntity entity) {
    Product resource = createModelWithId(entity.getId(), entity);
    BeanUtils.copyProperties(entity, resource);
    resource.setId(entity.getId().toString());
    resource.setTag(
        entity.getTags().stream().map(t -> new Tag().id(t.getId().toString()).name(t.getName()))
            .collect(toList()));

    return resource;
  }

  /**
   * Coverts the collection of Product entities to list of resources.
   *
   * @param entities
   */
  public List<Product> toListModel(Iterable<ProductEntity> entities) {
    if (Objects.isNull(entities)) {
      return Collections.emptyList();
    }
    return StreamSupport.stream(entities.spliterator(), false).map(p -> toModel(p))
        .collect(toList());
  }
}
