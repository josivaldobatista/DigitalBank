package com.jfb.digital_banking_gateway.core.usecase.customer.interactors;

import com.jfb.digital_banking_gateway.core.domain.models.Customer;
import com.jfb.digital_banking_gateway.core.usecase.customer.FindAllCustomerUseCase;
import com.jfb.digital_banking_gateway.dataprovider.client.CustomerClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindAllCustomerUseCaseImpl implements FindAllCustomerUseCase {

    private final CustomerClient customerClient;

    public FindAllCustomerUseCaseImpl(CustomerClient customerClient) {
        this.customerClient = customerClient;
    }

    @Override
    public List<Customer> findAllCustomers() {
        return customerClient.findAll();
    }
}
