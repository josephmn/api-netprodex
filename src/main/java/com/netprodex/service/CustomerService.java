package com.netprodex.service;

import com.netprodex.persistence.Cliente;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CustomerService {
    List<Cliente> findAllCustomer();
    Page<Cliente> pageAllCustomer(int page, int elements);
    Cliente findById(int id);
    Cliente saveCustomer(Cliente customer);
    Cliente updateCustomer(Cliente customer);
    void deleteCustomer(int id);
    boolean exists(int id);
    Cliente findByNameIgnoreCase(String name);
}
