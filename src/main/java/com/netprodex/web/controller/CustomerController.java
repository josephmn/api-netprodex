package com.netprodex.web.controller;

import com.netprodex.exception.BadRequestException;
import com.netprodex.exception.ResourceNotFoundException;
import com.netprodex.persistence.Cliente;
import com.netprodex.persistence.payload.ApiResponse;
import com.netprodex.persistence.payload.MessageError;
import com.netprodex.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/customer")
@Tag(name = "Customer", description = "Endpoint for customer")
@SecurityRequirement(name = "BearerAuth")
public class CustomerController {

    private final CustomerService customerService;
    private final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Operation(summary = "Get all customers", description = "Listing customers from BD")
    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> findAllCustomer() {
        // logger.info("Get controller all customer");
        List<Cliente> customer = this.customerService.findAllCustomer();
        if (customer == null || customer.isEmpty()) {
            throw new ResourceNotFoundException("customer");
        }
        return new ResponseEntity<>(
                new ApiResponse(customer),
                HttpStatus.OK
        );
    }

    @Operation(summary = "Pagination all customers", description = "Pagination all customers from BD")
    @GetMapping(value = "/page", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<Cliente>> pageAllCustomer(@RequestParam(defaultValue = "0") int page,
                                                         @RequestParam(defaultValue = "3") int elements) {
        return ResponseEntity.ok(this.customerService.pageAllCustomer(page, elements));
    }

    @Operation(summary = "Get customers by id", description = "Customers by id from BD")
    @GetMapping(value = "/findById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> findById(@PathVariable int id) {
        Cliente customer = this.customerService.findById(id);
        return new ResponseEntity<>(
                new ApiResponse(customer),
                HttpStatus.OK
        );
    }

    @Operation(summary = "Registry one customer", description = "Save one customer in BD")
    @PostMapping(value = "/save", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> saveCustomer(@RequestBody @Valid Cliente cliente) {
        Cliente customer = this.customerService.saveCustomer(cliente);
        return new ResponseEntity<>(
                new ApiResponse(customer),
                HttpStatus.CREATED
        );
    }

    @Operation(summary = "Update one customer", description = "Update one customer in BD")
    @PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updateCustomer(@RequestBody Cliente cliente) {
        Cliente customer = this.customerService.updateCustomer(cliente);
        if (cliente.getCustomerId() != null && this.customerService.exists(cliente.getCustomerId())) {
            return new ResponseEntity<>(
                    new ApiResponse(customer),
                    HttpStatus.OK
            );
        }
        throw new ResourceNotFoundException("customer", "customerId", cliente.getCustomerId());
    }

    @Operation(summary = "Delete one customer", description = "Delete one customer in BD")
    @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> deleteCustomer(@PathVariable int id) {
        try {
            if (this.customerService.exists(id)) {
                this.customerService.deleteCustomer(id);
            } else {
                throw new ResourceNotFoundException("customer", "id", id);
            }
        } catch (DataAccessException exception) {
            throw new BadRequestException(exception.getMessage());
        }
        return new ResponseEntity<>(
                new ApiResponse(new ArrayList<>()),
                HttpStatus.OK
        );
    }

}
