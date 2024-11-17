package com.jfb.digital_banking_data.core.usecase.impl;

import com.jfb.digital_banking_data.core.dataprovider.InsertCustomer;
import com.jfb.digital_banking_data.core.domain.Customer;
import com.jfb.digital_banking_data.core.usecase.InsertCustomerUseCase;
import org.springframework.stereotype.Component;

@Component
public class InsertCustomerUseCaseImpl implements InsertCustomerUseCase {

    private final InsertCustomer insertCustomer;

    public InsertCustomerUseCaseImpl(InsertCustomer insertCustomer) {
        this.insertCustomer = insertCustomer;
    }

    @Override
    public void execute(Customer customer) {
        insertCustomer.insert(customer);
    }
}
