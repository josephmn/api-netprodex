package com.netprodex.service;

import com.netprodex.persistence.entity.CustomerEntity;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    List<CustomerEntity> findAllCustomer();
    CustomerEntity findById(Integer id);
    CustomerEntity saveCustomer(CustomerEntity customer);
    CustomerEntity updateCustomer(CustomerEntity customer);
    void deleteCustomer(Integer id);
    boolean exists(Integer id);
}
