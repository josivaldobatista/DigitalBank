package com.jfb.digital_banking_gateway.core.usecase.customer;

import com.jfb.digital_banking_gateway.core.domain.models.Customer;

public interface UpdateCustomerUseCase {

    void execute(Customer model);
}
