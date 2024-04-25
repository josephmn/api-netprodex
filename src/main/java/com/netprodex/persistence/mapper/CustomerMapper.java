package com.netprodex.persistence.mapper;

import com.netprodex.persistence.Cliente;
import com.netprodex.persistence.entity.CustomerEntity;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    @Mappings({
            @Mapping(source = "idCustomer", target = "customerId"),
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "lastname", target = "lastname"),
            @Mapping(source = "email", target = "email"),
            @Mapping(source = "phoneNumber", target = "phone"),
            @Mapping(source = "address", target = "address")

    })
    Cliente toCliente(CustomerEntity customer);

    List<Cliente> toCustomers(List<CustomerEntity> customers);

    @InheritInverseConfiguration
    CustomerEntity toCustomerEntity(Cliente cliente);
}
