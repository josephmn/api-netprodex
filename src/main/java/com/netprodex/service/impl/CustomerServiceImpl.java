package com.netprodex.service.impl;

import com.netprodex.persistence.entity.CustomerEntity;
import com.netprodex.persistence.repository.CustomerRepository;
import com.netprodex.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<CustomerEntity> findAllCustomer() {
        return this.customerRepository.findAll();
    }

    @Override
    public CustomerEntity saveCustomer(CustomerEntity customer) {
        return this.customerRepository.save(customer);
    }

    @Override
    public CustomerEntity updateCustomer(CustomerEntity customer) {
        return this.customerRepository.save(customer);
    }

    @Override
    public void deleteCustomer(Integer id) {
        this.customerRepository.deleteById(id);
    }

    @Override
    public boolean exists(Integer id) {
        return this.customerRepository.existsById(id);
    }
}
