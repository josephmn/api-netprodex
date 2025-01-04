package com.netprodex.persistence.repository;

import com.netprodex.persistence.entity.CustomerEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CustomerRepositoryTest {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    TestEntityManager testEntityManager;

    @BeforeEach
    void setUp() {
        String[][] customers = {
                {"Manuel", "Jona", "manuele.jb@hotmail.com", "987654321", "USA"},
                {"Car", "Tomas", "car.tom@hotmail.com", "934736487", "USA"},
                {"Sonia", "Gonzales", "maria.gonzalez@gmail.com", "982632746", "Spain"},
                {"John", "Doe", "john.doe@outlook.es", "946378463", "USA"},
                {"Jane", "Smith", "jane.smith@yahoo.com", "923635473", "Canada"},
                {"Mary", "Techs", "mary.techs@example.com", "977463744", "Canada"}
        };

        for (String[] customerData: customers) {
            CustomerEntity customer = CustomerEntity.builder()
                    .name(customerData[0])
                    .lastname(customerData[1])
                    .email(customerData[2])
                    .phoneNumber(customerData[3])
                    .address(customerData[4])
                    .build();
            testEntityManager.persist(customer);
        }
    }

    // For ideal scenario
    @Test
    public void findByNameIgnoreCaseFound(){
        Optional<CustomerEntity> customerOptional = customerRepository.findByNameIgnoreCase("John");
        assertTrue(customerOptional.isPresent(), "customer not found");

        CustomerEntity customer = customerOptional.get();
        assertEquals("John", customer.getName());
        System.out.println("customer: " + customer);
    }

    // For response null
    @Test
    public void findByNameIgnoreCaseNotFound(){
        Optional<CustomerEntity> customerOptional = customerRepository.findByNameIgnoreCase("Joseph");
        assertEquals(customerOptional, Optional.empty());
    }
}