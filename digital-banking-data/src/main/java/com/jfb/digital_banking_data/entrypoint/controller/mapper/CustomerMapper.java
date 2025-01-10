package com.jfb.digital_banking_data.entrypoint.controller.mapper;

import com.jfb.digital_banking_data.core.domain.Customer;
import com.jfb.digital_banking_data.dataprovider.repository.entity.CustomerEntity;
import com.jfb.digital_banking_data.entrypoint.controller.request.CustomerRequest;
import com.jfb.digital_banking_data.entrypoint.controller.request.CustomerUpdateRequest;
import com.jfb.digital_banking_data.entrypoint.controller.response.CustomerResponse;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    public Customer toCustomer(CustomerRequest request) {
        Customer customer = new Customer();
        customer.setName(request.name());
        customer.setEmail(request.email());
        customer.setUsername(request.username());
        customer.setPassword(request.password());
        customer.setBirthDate(request.birthDate());
        customer.setCpfCnpj(request.cpfCnpj());
        customer.setStatus(request.status());

        return customer;
    }

    public CustomerResponse toCustomerResponse(Customer customer) {
        return new CustomerResponse(
                customer.getName(),
                customer.getEmail(),
                customer.getUsername(),
                customer.getBirthDate(),
                customer.getCpfCnpj(),
                customer.getStatus()
        );
    }

    public Customer toModelUpdate(CustomerUpdateRequest customerUpdateRequest) {
        return new Customer(
                customerUpdateRequest.name(),
                customerUpdateRequest.email()
        );
    }
}
