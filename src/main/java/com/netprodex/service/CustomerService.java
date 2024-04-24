package com.netprodex.service;

import com.netprodex.persistence.entity.CustomerEntity;

import java.util.List;

public interface CustomerService {
    List<CustomerEntity> findAllCustomer();
    CustomerEntity saveCustomer(CustomerEntity customer);
    CustomerEntity updateCustomer(CustomerEntity customer);
    void deleteCustomer(Integer id);
    boolean exists(Integer id);
}
