package com.jfb.digital_banking_data.core.usecase;

import com.jfb.digital_banking_data.core.domain.Customer;

public interface UpdateCustomerUseCase {

    void execute(Customer customer, String id);
}
