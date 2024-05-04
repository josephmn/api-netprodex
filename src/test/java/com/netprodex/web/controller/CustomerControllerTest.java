package com.netprodex.web.controller;

import com.netprodex.persistence.Cliente;
import com.netprodex.persistence.repository.UserRepository;
import com.netprodex.service.CustomerService;
import com.netprodex.service.UserDetailServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.RequestPostProcessor;


import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CustomerController.class)
class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private UserDetailServiceImpl userDetailServiceImpl;

    private Cliente responseCustomer;

    @BeforeEach
    void setUp() {
        responseCustomer = Cliente.builder()
                .customerId(1)
                .name("John")
                .lastname("Doe")
                .email("john.doe@outlook.es")
                .phone("946378463")
                .address("USA")
                .build();
    }

    public static RequestPostProcessor admin() {
        return user("admin").roles("ADMIN");
    }

    @Test
    @DisplayName("Test to save customer")
    void saveCustomer() throws Exception {
        Cliente postCustomer = Cliente.builder()
                .name("John")
                .lastname("Doe")
                .email("john.doe@outlook.es")
                .phone("946378463")
                .address("USA")
                .build();
        Mockito.when(customerService.saveCustomer(postCustomer)).thenReturn(responseCustomer);
        mockMvc.perform(post("/api/v1/customer/save")
                        .with(admin())
                        //.with(user("admin").password("1234").roles("ADMIN")) //ok
                        //.with(httpBasic("admin", "1234"))
                        //.header("Authorization", "Bearer " + token)
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "    \"name\": \"John\",\n" +
                                "    \"lastname\": \"Doe\",\n" +
                                "    \"email\": \"john.doe@outlook.es\",\n" +
                                "    \"phone\": \"946378463\",\n" +
                                "    \"address\": \"USA\"\n" +
                                "}"))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("Test find customer by id")
    void findCustomerById() throws Exception {
        Mockito.when(customerService.findById(1)).thenReturn(responseCustomer);
        mockMvc.perform(get("/api/v1/customer/findById/1")
                        .with(admin())
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.object.name").value(responseCustomer.getName()));
    }
}