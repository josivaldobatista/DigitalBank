package com.jfb.digital_banking_data.core.usecase;

import com.jfb.digital_banking_data.core.domain.Customer;

import java.util.Optional;

public interface FindCustomerByIdUseCase {

    Customer findById(String id);
}
