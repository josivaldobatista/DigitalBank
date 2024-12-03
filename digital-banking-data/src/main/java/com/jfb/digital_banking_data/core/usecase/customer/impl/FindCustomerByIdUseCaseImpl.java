package com.jfb.digital_banking_data.core.usecase.customer.impl;

import com.jfb.digital_banking_data.core.dataprovider.customer.FindCustomer;
import com.jfb.digital_banking_data.core.domain.Customer;
import com.jfb.digital_banking_data.core.exception.ResourceNotFoundException;
import com.jfb.digital_banking_data.core.usecase.customer.FindCustomerByIdUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class FindCustomerByIdUseCaseImpl implements FindCustomerByIdUseCase {

    private static final Logger logger = LoggerFactory.getLogger(FindCustomerByIdUseCaseImpl.class);
    private final FindCustomer findCustomer;

    public FindCustomerByIdUseCaseImpl(FindCustomer findCustomer) {
        this.findCustomer = findCustomer;
    }

    @Override
    public Customer findById(String id) {
        logger.info("Finding customer with ID: {}", id);

        return findCustomer.findById(id).orElseThrow(() -> {
            logger.error("Customer with ID {} not found", id);
            return new ResourceNotFoundException("Customer with ID " + id + " not found");
        });
    }
}
