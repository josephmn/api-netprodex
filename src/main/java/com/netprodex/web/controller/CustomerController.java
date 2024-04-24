package com.netprodex.web.controller;

import com.netprodex.persistence.entity.CustomerEntity;
import com.netprodex.service.impl.CustomerServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customer")
@Tag(name = "Customer", description = "Endpoint for customer")
public class CustomerController {

    private final CustomerServiceImpl customerServiceImpl;
    private final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    public CustomerController(CustomerServiceImpl customerServiceImpl) {
        this.customerServiceImpl = customerServiceImpl;
    }

    @Operation(summary = "Get all customers", description = "Listing customers from BD")
    @GetMapping(value = "/all", produces = "application/json")
    public ResponseEntity<List<CustomerEntity>> findAllCustomer() {
        logger.info("Get controller all customer");
        List<CustomerEntity> customer = this.customerServiceImpl.findAllCustomer();
        if (customer.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(customer, HttpStatus.OK);
        }
    }

}
