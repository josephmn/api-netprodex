package com.netprodex.service.impl;

import com.netprodex.exception.BadRequestException;
import com.netprodex.exception.ResourceNotFoundException;
import com.netprodex.persistence.Cliente;
import com.netprodex.persistence.entity.CustomerEntity;
import com.netprodex.persistence.mapper.CustomerMapper;
import com.netprodex.persistence.repository.CustomerPagSortRepository;
import com.netprodex.persistence.repository.CustomerRepository;
import com.netprodex.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerPagSortRepository customerPagSortRepository;
    private final CustomerMapper mapper;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerPagSortRepository customerPagSortRepository, CustomerMapper mapper) {
        this.customerRepository = customerRepository;
        this.customerPagSortRepository = customerPagSortRepository;
        this.mapper = mapper;
    }

    @Override
    public List<Cliente> findAllCustomer() {
        List<CustomerEntity> customers = this.customerRepository.findAll();
        return mapper.toClientes(customers);
    }

    @Override
    public Page<Cliente> pageAllCustomer(int page, int elements) {
        Pageable pageRequest = PageRequest.of(page, elements);
        return mapper.toCustomersPage(this.customerPagSortRepository.findAll(pageRequest));
    }

    @Override
    public Cliente findById(int id) {
        Optional<CustomerEntity> customer = this.customerRepository.findById(id);

        if (customer.isPresent()) {
            return mapper.toCliente(customer.get());
        } else {
            throw new ResourceNotFoundException("customer", "customerId", id);
        }
    }

    @Transactional
    @Override
    public Cliente saveCustomer(Cliente cliente) {
        CustomerEntity customer = mapper.toCustomerEntity(cliente);
        try {
            return mapper.toCliente(this.customerRepository.save(customer));
        } catch (DataAccessException exception) {
            throw new BadRequestException(exception.getMessage());
        }
    }

    @Override
    public Cliente updateCustomer(Cliente cliente) {
        CustomerEntity customer = mapper.toCustomerEntity(cliente);
        return mapper.toCliente(this.customerRepository.save(customer));
    }

    @Override
    public void deleteCustomer(int id) {
        this.customerRepository.deleteById(id);
    }

    @Override
    public boolean exists(int id) {
        return this.customerRepository.existsById(id);
    }
}
