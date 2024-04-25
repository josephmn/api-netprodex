package com.netprodex.persistence.mapper;

import com.netprodex.persistence.Cliente;
import com.netprodex.persistence.entity.CustomerEntity;
import org.mapstruct.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

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

    List<Cliente> toClientes(List<CustomerEntity> customers);

    //Page<Cliente> toCustomersPage(Page<CustomerEntity> customersPage);

    default Page<Cliente> toCustomersPage(Page<CustomerEntity> customersPage) {
        List<Cliente> clienteList = toClientes(customersPage.getContent());
        return new PageImpl<>(clienteList, customersPage.getPageable(), customersPage.getTotalElements());
    }

    @InheritInverseConfiguration
    CustomerEntity toCustomerEntity(Cliente cliente);
}
