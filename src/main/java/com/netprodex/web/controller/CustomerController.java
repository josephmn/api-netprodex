package com.netprodex.web.controller;

import com.netprodex.persistence.entity.CustomerEntity;
import com.netprodex.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customer")
@Tag(name = "Customer", description = "Endpoint for customer")
public class CustomerController {

    private final CustomerService customerService;
    private final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Operation(summary = "Get all customers", description = "Listing customers from BD")
    @GetMapping(value = "/all", produces = "application/json")
    public ResponseEntity<List<CustomerEntity>> findAllCustomer() {
        logger.info("Get controller all customer");
        List<CustomerEntity> customer = this.customerService.findAllCustomer();
        if (customer.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(customer, HttpStatus.OK);
        }
    }

    @Operation(summary = "Registry one customer", description = "Save one customer in BD")
    @PostMapping(value = "/save", produces = "application/json")
    public ResponseEntity<CustomerEntity> saveCustomer(@RequestBody CustomerEntity customer) {
        return new ResponseEntity<>(this.customerService.saveCustomer(customer), HttpStatus.CREATED);
    }

    @Operation(summary = "Update one customer", description = "Update one customer in BD")
    @PutMapping(value = "/update", produces = "application/json")
    public ResponseEntity<CustomerEntity> updateCustomer(@RequestBody CustomerEntity customer) {
        if (customer.getIdCustomer() != null && this.customerService.exists(customer.getIdCustomer())) {
            return ResponseEntity.ok(this.customerService.updateCustomer(customer));
        }
        return ResponseEntity.badRequest().build();
    }

}
