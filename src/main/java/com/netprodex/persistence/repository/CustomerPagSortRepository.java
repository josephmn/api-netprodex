package com.netprodex.persistence.repository;

import com.netprodex.persistence.entity.CustomerEntity;
import org.springframework.data.repository.ListPagingAndSortingRepository;

public interface CustomerPagSortRepository extends ListPagingAndSortingRepository<CustomerEntity, Integer> {
}
