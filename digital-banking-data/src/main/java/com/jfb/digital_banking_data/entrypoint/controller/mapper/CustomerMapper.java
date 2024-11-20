package com.jfb.digital_banking_data.entrypoint.controller.mapper;

import com.jfb.digital_banking_data.core.domain.Customer;
import com.jfb.digital_banking_data.dataprovider.repository.entity.CustomerEntity;
import com.jfb.digital_banking_data.entrypoint.controller.request.CustomerRequest;
import com.jfb.digital_banking_data.entrypoint.controller.response.CustomerResponse;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    public Customer toCustomer(CustomerRequest request) {
        Customer customer = new Customer();
        customer.setName(request.name());
        customer.setEmail(request.email());
        customer.setBirthDate(request.birthDate());
        customer.setCpfCnpj(request.cpfCnpj());
        customer.setStatus(request.status());

        return customer;
    }

    public CustomerResponse toCustomerResponse(Customer customer) {
        return new CustomerResponse(
                customer.getName(),
                customer.getEmail(),
                customer.getBirthDate(),
                customer.getCpfCnpj(),
                customer.getStatus()
        );
    }
}
