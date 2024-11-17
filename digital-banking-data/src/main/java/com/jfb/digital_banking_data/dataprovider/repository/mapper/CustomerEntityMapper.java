package com.jfb.digital_banking_data.dataprovider.repository.mapper;

import com.jfb.digital_banking_data.core.domain.Customer;
import com.jfb.digital_banking_data.dataprovider.repository.entity.CustomerEntity;
import org.springframework.stereotype.Component;

@Component
public class CustomerEntityMapper {

    public CustomerEntity toEntity(Customer customer) {
        CustomerEntity entity = new CustomerEntity();
        entity.setName(customer.getName());
        entity.setEmail(customer.getEmail());
        entity.setBirthDate(customer.getBirthDate());
        entity.setCpfCnpj(customer.getCpfCnpj());
        return entity;
    }
}
