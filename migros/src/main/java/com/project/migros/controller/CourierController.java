package com.project.migros.controller;

import com.project.migros.base.dto.Courier;
import com.project.migros.service.ICourierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/courier")
public class CourierController {

  @Autowired
  private ICourierService courierService;

  @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Courier> createCourierDistance(@RequestBody Courier courier) {
    return new ResponseEntity<>(courierService.createCourierDistance(courier), HttpStatus.OK);
  }

  @GetMapping(value = "/{courierId}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Double> inquireCreditScoreByCustomer(@PathVariable("courierId") Long courierId) {
    return ResponseEntity.ok(courierService.getTotalTravelDistance(courierId));
  }
}
