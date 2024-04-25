package com.netprodex.service.impl;

import com.netprodex.persistence.Cliente;
import com.netprodex.persistence.entity.CustomerEntity;
import com.netprodex.persistence.mapper.CustomerMapper;
import com.netprodex.persistence.repository.CustomerRepository;
import com.netprodex.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper mapper;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper mapper) {
        this.customerRepository = customerRepository;
        this.mapper = mapper;
    }

    @Override
    public List<Cliente> findAllCustomer() {
        List<CustomerEntity> customers = this.customerRepository.findAll();
        return mapper.toCustomers(customers);
    }

    @Override
    public Cliente findById(Integer id) {
        Optional<CustomerEntity> customer = this.customerRepository.findById(id);

        if (customer.isPresent()) {
            return mapper.toCliente(customer.get());
        } else {
            throw new RuntimeException("Customer not found in BD, with id: " + id);
        }
    }

    @Override
    public Cliente saveCustomer(Cliente cliente) {
        CustomerEntity customer = mapper.toCustomerEntity(cliente);
        return mapper.toCliente(this.customerRepository.save(customer));
    }

    @Override
    public Cliente updateCustomer(Cliente cliente) {
        CustomerEntity customer = mapper.toCustomerEntity(cliente);
        return mapper.toCliente(this.customerRepository.save(customer));
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
