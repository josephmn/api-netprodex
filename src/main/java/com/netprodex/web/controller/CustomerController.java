package com.netprodex.web.controller;

import com.netprodex.persistence.entity.CustomerEntity;
import com.netprodex.persistence.repository.CustomerRepository;
import com.netprodex.service.impl.CustomerServiceImpl;
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
public class CustomerController {

    private final CustomerServiceImpl customerServiceImpl;

    @Autowired
    public CustomerController(CustomerServiceImpl customerServiceImpl) {
        this.customerServiceImpl = customerServiceImpl;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<List<CustomerEntity>> findAllCustomer() {
        return ResponseEntity.ok(this.customerServiceImpl.findAllCustomer());
    }

}
