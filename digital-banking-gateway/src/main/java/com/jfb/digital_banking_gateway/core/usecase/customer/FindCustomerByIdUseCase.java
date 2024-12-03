package com.jfb.digital_banking_gateway.core.usecase.customer;

import com.jfb.digital_banking_gateway.core.domain.models.Customer;

import java.util.Optional;

public interface FindCustomerByIdUseCase {
    Optional<Customer> findById(String id);
}
