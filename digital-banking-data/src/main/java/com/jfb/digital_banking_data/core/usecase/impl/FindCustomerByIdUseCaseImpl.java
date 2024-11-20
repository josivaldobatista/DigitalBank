package com.jfb.digital_banking_data.core.usecase.impl;

import com.jfb.digital_banking_data.core.dataprovider.FindCustomer;
import com.jfb.digital_banking_data.core.domain.Customer;
import com.jfb.digital_banking_data.core.exception.ResourceNotFoundException;
import com.jfb.digital_banking_data.core.usecase.FindCustomerByIdUseCase;
import org.springframework.stereotype.Component;

@Component
public class FindCustomerByIdUseCaseImpl implements FindCustomerByIdUseCase {

    private final FindCustomer findCustomer;

    public FindCustomerByIdUseCaseImpl(FindCustomer findCustomer) {
        this.findCustomer = findCustomer;
    }

    @Override
    public Customer findById(String id) {
        return findCustomer.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Customer with ID " + id + " not found"));
    }
}
