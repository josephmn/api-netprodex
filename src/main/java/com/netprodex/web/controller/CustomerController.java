package com.netprodex.web.controller;

import com.netprodex.persistence.Cliente;
import com.netprodex.persistence.entity.CustomerEntity;
import com.netprodex.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
    public ResponseEntity<List<Cliente>> findAllCustomer() {
        // logger.info("Get controller all customer");
        List<Cliente> customer = this.customerService.findAllCustomer();
        if (customer.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(customer, HttpStatus.OK);
        }
    }

    @Operation(summary = "Pagination all customers", description = "Pagination all customers from BD")
    @GetMapping(value = "/page", produces = "application/json")
    public ResponseEntity<Page<Cliente>> pageAllCustomer(@RequestParam(defaultValue = "0") int page,
                                                                @RequestParam(defaultValue = "3") int elements) {
        return ResponseEntity.ok(this.customerService.pageAllCustomer(page, elements));
    }

    @Operation(summary = "Get customers by id", description = "Customers by id from BD")
    @GetMapping(value = "/findById/{id}", produces = "application/json")
    public ResponseEntity<Cliente> findById(@PathVariable int id) {
        return ResponseEntity.ok(this.customerService.findById(id));
    }

    @Operation(summary = "Registry one customer", description = "Save one customer in BD")
    @PostMapping(value = "/save", produces = "application/json")
    public ResponseEntity<Cliente> saveCustomer(@RequestBody Cliente cliente) {
        return new ResponseEntity<>(this.customerService.saveCustomer(cliente), HttpStatus.CREATED);
    }

    @Operation(summary = "Update one customer", description = "Update one customer in BD")
    @PutMapping(value = "/update", produces = "application/json")
    public ResponseEntity<Cliente> updateCustomer(@RequestBody Cliente cliente) {
        if (cliente.getCustomerId() != null && this.customerService.exists(cliente.getCustomerId())) {
            return ResponseEntity.ok(this.customerService.updateCustomer(cliente));
        }
        return ResponseEntity.badRequest().build();
    }

    @Operation(summary = "Delete one customer", description = "Delete one customer in BD")
    @DeleteMapping(value = "/delete/{id}", produces = "application/json")
    public ResponseEntity<Void> deleteCustomer(@PathVariable int id) {
        if (this.customerService.exists(id)) {
            this.customerService.deleteCustomer(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

}
