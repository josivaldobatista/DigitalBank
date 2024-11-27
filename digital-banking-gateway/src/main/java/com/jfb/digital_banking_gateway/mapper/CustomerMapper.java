package com.jfb.digital_banking_gateway.mapper;

import com.jfb.digital_banking_gateway.adapter.request.CustomerRequest;
import com.jfb.digital_banking_gateway.domain.model.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    public Customer toModel(CustomerRequest request) {
        Customer customer = new Customer();
        customer.setName(request.name());
        customer.setEmail(request.email());
        customer.setBirthDate(request.birthDate());
        customer.setCpfCnpj(request.cpfCnpj());
        customer.setStatus(request.status());
        return customer;
    }

    public CustomerRequest toRequest(Customer customer) {
        return new CustomerRequest(
                customer.getName(),
                customer.getEmail(),
                customer.getBirthDate(),
                customer.getCpfCnpj(),
                customer.getStatus()
        );
    }
}
