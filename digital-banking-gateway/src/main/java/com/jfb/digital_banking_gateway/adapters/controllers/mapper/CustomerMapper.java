package com.jfb.digital_banking_gateway.adapters.controllers.mapper;

import com.jfb.digital_banking_gateway.adapters.controllers.request.CustomerRequest;
import com.jfb.digital_banking_gateway.core.domain.models.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    public Customer toModel(CustomerRequest request) {
        Customer customer = new Customer();
        customer.setId(request.id());
        customer.setName(request.name());
        customer.setEmail(request.email());
        customer.setBirthDate(request.birthDate());
        customer.setCpfCnpj(request.cpfCnpj());
        customer.setStatus(request.status());
        return customer;
    }
}

