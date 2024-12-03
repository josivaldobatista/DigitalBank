package com.jfb.digital_banking_gateway.core.usecase.customer.interactors;

import com.jfb.digital_banking_gateway.core.domain.models.Customer;
import com.jfb.digital_banking_gateway.core.usecase.customer.FindCustomerByIdUseCase;
import com.jfb.digital_banking_gateway.core.usecase.exceptions.ResourceNotFoundException;
import com.jfb.digital_banking_gateway.dataprovider.client.CustomerClient;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FindCustomerByIdUseCaseImpl implements FindCustomerByIdUseCase {

    private final CustomerClient customerClient;

    public FindCustomerByIdUseCaseImpl(CustomerClient customerClient) {
        this.customerClient = customerClient;
    }

    @Override
    public Optional<Customer> findById(String id) {
        return Optional.ofNullable(customerClient.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + id)));
    }
}
