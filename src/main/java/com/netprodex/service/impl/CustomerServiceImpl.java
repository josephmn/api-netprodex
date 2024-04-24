package com.netprodex.service.impl;

import com.netprodex.persistence.entity.CustomerEntity;
import com.netprodex.persistence.repository.CustomerRepository;
import com.netprodex.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public CustomerEntity findById(Integer id) {
        Optional<CustomerEntity> customer = this.customerRepository.findById(id);

        if (customer.isPresent()) {
            return customer.get();
        } else {
            throw new RuntimeException("Customer not found in BD, with id: " + id);
        }
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
