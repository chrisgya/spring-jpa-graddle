package com.chrisgya.springjpagraddle.controller;


import com.chrisgya.springjpagraddle.ShipmentApi;
import com.chrisgya.springjpagraddle.hateoas.ShipmentRepresentationModelAssembler;
import com.chrisgya.springjpagraddle.model.Shipment;
import com.chrisgya.springjpagraddle.service.ShipmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
public class ShipmentController implements ShipmentApi {

  private ShipmentService service;
  private final ShipmentRepresentationModelAssembler assembler;

  public ShipmentController(ShipmentService service, ShipmentRepresentationModelAssembler assembler) {
    this.service = service;
    this.assembler = assembler;
  }

  @Override
  public ResponseEntity<List<Shipment>> getShipmentByOrderId(@NotNull @Valid String id) {
    return ResponseEntity.ok(assembler.toListModel(service.getShipmentByOrderId(id)));
  }
}
