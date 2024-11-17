package com.jfb.digital_banking_data.entrypoint.controller.mapper;

import com.jfb.digital_banking_data.core.domain.Customer;
import com.jfb.digital_banking_data.entrypoint.controller.request.CustomerRequest;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    public static Customer toCustomer(CustomerRequest request) {
        Customer customer = new Customer();
        customer.setName(request.name());
        customer.setEmail(request.email());
        customer.setBirthDate(request.birthDate());
        customer.setCpfCnpj(request.cpfCnpj());

        return customer;
    }
}
