package com.netprodex.service;

import com.netprodex.persistence.Cliente;

import java.util.List;

public interface CustomerService {
    List<Cliente> findAllCustomer();
    Cliente findById(Integer id);
    Cliente saveCustomer(Cliente customer);
    Cliente updateCustomer(Cliente customer);
    void deleteCustomer(Integer id);
    boolean exists(Integer id);
}
