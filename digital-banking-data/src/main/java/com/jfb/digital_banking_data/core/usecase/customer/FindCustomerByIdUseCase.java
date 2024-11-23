package com.jfb.digital_banking_data.core.usecase.customer;

import com.jfb.digital_banking_data.core.domain.Customer;

public interface FindCustomerByIdUseCase {

    Customer findById(String id);
}
