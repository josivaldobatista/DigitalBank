package com.jfb.digital_banking_data.core.usecase;

import com.jfb.digital_banking_data.core.domain.Customer;

import java.util.List;

public interface FindAllCustomerUseCase {

    List<Customer> execute();
}
