package com.jfb.digital_banking_data.dataprovider.repository.mapper;

import com.jfb.digital_banking_data.core.domain.Customer;
import com.jfb.digital_banking_data.dataprovider.repository.entity.CustomerEntity;
import org.springframework.stereotype.Component;

@Component
public class CustomerEntityMapper {

    public CustomerEntity toEntity(Customer customer) {
        CustomerEntity entity = new CustomerEntity();
        entity.setId(customer.getId());
        entity.setName(customer.getName());
        entity.setEmail(customer.getEmail());
        entity.setBirthDate(customer.getBirthDate());
        entity.setCpfCnpj(customer.getCpfCnpj());
        entity.setStatus(customer.getStatus());
        return entity;
    }

    public Customer toCustomer(CustomerEntity entity) {
        Customer customer = new Customer();
        customer.setId(entity.getId());
        customer.setName(entity.getName());
        customer.setEmail(entity.getEmail());
        customer.setBirthDate(entity.getBirthDate());
        customer.setCpfCnpj(entity.getCpfCnpj());
        customer.setStatus(entity.getStatus());
        return customer;
    }
}
