package com.netprodex.service;

import com.netprodex.persistence.Cliente;
import com.netprodex.persistence.entity.CustomerEntity;
import com.netprodex.persistence.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static com.netprodex.persistence.Cliente.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CustomerServiceTest {

    @Autowired
    private CustomerService customerService;

    @MockBean
    private CustomerRepository customerRepository;

    @BeforeEach
    void setUp() {
        CustomerEntity client = CustomerEntity.builder()
                .idCustomer(1)
                .name("John")
                .lastname("Doe")
                .email("john.doe@outlook.es")
                .phoneNumber("946378463")
                .address("USA")
                .build();
        Mockito.when(customerRepository.findByNameIgnoreCase("John")).thenReturn(Optional.of(client));
    }

    @Test
    @DisplayName("Test find by name with name valid")
    public void findByNameIgnoreCaseShouldFound() {
        String customerName = "John";
        Cliente customer = customerService.findByNameIgnoreCase(customerName);
        assertEquals(customerName, customer.getName());
        System.out.println("Customer: " + customer);
    }
}