package com.jfb.digital_banking_gateway.adapters.controllers.mapper;

import com.jfb.digital_banking_gateway.adapters.controllers.request.CustomerRequest;
import com.jfb.digital_banking_gateway.adapters.controllers.request.CustomerUpdateRequest;
import com.jfb.digital_banking_gateway.adapters.controllers.response.CustomerResponse;
import com.jfb.digital_banking_gateway.core.domain.models.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    public Customer toModel(CustomerRequest request) {
        return new Customer(
                request.id(),
                request.name(),
                request.email(),
                request.birthDate(),
                request.cpfCnpj(),
                request.status()
        );
    }

    public Customer toModelUpdate(CustomerUpdateRequest customerUpdateRequest) {
        return new Customer(
                customerUpdateRequest.name(),
                customerUpdateRequest.email()
        );
    }

    public CustomerResponse toResponse(Customer customer) {
        return new CustomerResponse(
                customer.getName(),
                customer.getEmail(),
                customer.getBirthDate(),
                customer.getCpfCnpj(),
                customer.getStatus()
        );
    }
}
