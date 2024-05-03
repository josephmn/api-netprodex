package com.netprodex.persistence.repository;

import com.netprodex.persistence.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Integer> {
    // For test unitary
    Optional<CustomerEntity> findByNameIgnoreCase(String name);
}
