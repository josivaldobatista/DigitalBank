package com.jfb.digital_banking_gateway.core.usecase.customer;

import com.jfb.digital_banking_gateway.core.domain.models.Customer;

public interface InsertCustomerUseCase {

    void execute(Customer customer);
}

